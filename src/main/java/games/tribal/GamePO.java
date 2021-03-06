package games.tribal;

import framework.Page;
import framework.decorator.element.WebControl;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class GamePO extends Page {

    @FindBy(xpath = "//input[@id='user']")
    public WebControl loginInput;

    @FindBy(xpath = "//input[@id='password']")
    public WebControl passwordInput;

    @FindBy(xpath = "//a[@class='login_button']/span[2]")
    public WebControl submitButton;

    @FindBy(xpath = "//span[@class='world_button_active']")
    public WebControl worldButton;

    @FindBy(xpath = "//span[@class='world_button_active']")
    public List<WebControl> worldButtonList;

    @FindBy(xpath = "//a[contains(@id,'main_buildlink_')][not(contains(@id,'cheap'))]")
    public WebControl buildButton;

    @FindBy(xpath = "//a[contains(@id,'main_buildlink_')][not(contains(@id,'cheap'))]")
    public List<WebControl> buildButtonList;
    public GamePO(WebDriver driver) {
        super(driver);
    }
}
