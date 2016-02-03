package games.tribal;

import framework.Page;
import framework.decorator.element.WebControl;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class GamePO extends Page {

    @FindBy(xpath = "//form[@action='login.php']//select[@name='server']")
    public WebControl server;

    public GamePO(WebDriver driver) {
        super(driver);
    }
}
