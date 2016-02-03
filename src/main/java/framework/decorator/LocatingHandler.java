package framework.decorator;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class LocatingHandler implements InvocationHandler {
    private final ElementLocator locator;

    public LocatingHandler(ElementLocator locator) {
        this.locator = locator;
    }

    public Object invoke(Object object, Method method, Object[] objects) throws Throwable {
        if("toString".equals(method.getName())) {
            return "Proxy element for: " + this.locator.toString();
        }
        WebElement element;
        try {
            element = this.locator.findElement();
        } catch (NoSuchElementException var7) {
            throw var7;
        }

        if("getWrappedElement".equals(method.getName())) {
            return element;
        } else {
            try {
                return method.invoke(element, objects);
            } catch (InvocationTargetException var6) {
                throw var6.getCause();
            }
        }
    }
}
