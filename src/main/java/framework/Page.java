package framework;

import framework.decorator.FieldDecorator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class Page {
    public Page(WebDriver driver) {
        PageFactory.initElements(new FieldDecorator(driver), this);
    }
}
