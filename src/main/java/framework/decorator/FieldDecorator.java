package framework.decorator;

import framework.decorator.element.WebControl;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.internal.WrapsElement;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.internal.LocatingElementListHandler;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.util.List;

public class FieldDecorator extends DefaultFieldDecorator {

    private SearchContext searchContext;

    public FieldDecorator(SearchContext searchContext) {
        super(new DefaultElementLocatorFactory(searchContext));
        this.searchContext = searchContext;
    }

    @Override
    public Object decorate(ClassLoader loader, Field field) {
        Class<?> clazz = genericChecker(field.getType(), field);
        if (WebElement.class.isAssignableFrom(clazz) && (!clazz.getName().equals(WebElement.class.getName()))) {
            Class<WebControl> dClass = (Class<WebControl>) clazz;
            return proxyLocatorGenerator(loader, dClass, field);
        }
        return super.decorate(loader, field);
    }


    private Class<?> genericChecker(Class<?> clazz, Field field) {
        if (List.class.isAssignableFrom(clazz)) {
            return (Class<?>) ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0];
        } else
            return clazz;
    }

    private Object proxyLocatorGenerator(ClassLoader loader, Class<WebControl> dClass, Field field) {
        ElementLocator locator = factory.createLocator(field);
        if (List.class.isAssignableFrom(field.getType())) {
            return Proxy.newProxyInstance(loader, new Class[]{List.class}, new Handler( locator, dClass, getName(field), getPage(field)));
        } else {
            try {
                return dClass.getConstructor(WebElement.class, String.class, String.class)
                        .newInstance(proxyForLocator(loader, locator), getName(field), getPage(field));
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private String getName(Field field) {
        return field.getName();
    }

    private String getPage(Field field) {
        return field.getDeclaringClass().getSimpleName();
    }

    @Override
    protected WebElement proxyForLocator(ClassLoader loader, ElementLocator locator) {
        LocatingHandler handler = new LocatingHandler(locator);
        WebElement proxy = (WebElement) Proxy.newProxyInstance(loader, new Class[]{WebElement.class, WrapsElement.class, Locatable.class}, handler);
        return proxy;
    }

    @Override
    protected List<WebElement> proxyForListLocator(ClassLoader loader, ElementLocator locator) {
        LocatingElementListHandler handler = new LocatingElementListHandler(locator);
        List proxy = (List) Proxy.newProxyInstance(loader, new Class[]{List.class}, handler);
        return proxy;
    }
}