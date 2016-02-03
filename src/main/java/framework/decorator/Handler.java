package framework.decorator;

import framework.decorator.element.WebControl;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Handler implements InvocationHandler {
    private final ElementLocator locator;
    private final Class<WebControl> clazz;
    private final String name;
    private final String page;

    public Handler(ElementLocator locator, Class<WebControl> clazz, String name, String page) {
        this.locator = locator;
        this.clazz = clazz;
        this.name = name;
        this.page = page;

    }

    public Object invoke(Object object, Method method, Object[] objects) throws Throwable {
        List<WebElement> realList;
        realList = locator.findElements();
        List<WebControl> customs = new ArrayList<>();
        for (WebElement element : realList) {
            customs.add(clazz.getConstructor(WebElement.class, String.class, String.class).newInstance(element, name, page));
        }
        return method.invoke(customs, objects);
    }
}