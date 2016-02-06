package games.botvaAvatar;

import framework.Page;
import framework.decorator.element.WebControl;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class GamePO extends Page {

    @FindBy(xpath = "//form[@action='login.php']//select[@name='server']")
    public WebControl server;

    @FindBy(xpath = "//form[@action='login.php']//input[@placeholder='E-mail']")
    public WebControl email;

    @FindBy(xpath = "//form[@action='login.php']//input[@name='password']")
    public WebControl password;

    @FindBy(xpath = "//form[@action='login.php']//input[@type='submit']")
    public WebControl submit;

    @FindBy(id = "l_popup_close")
    public WebControl lPopupClose;

    @FindBy(xpath = "//a[@id='field_potion_2_1']")
    public WebControl fieldPotion21;

    @FindBy(xpath = "//div[@class='box_x_button hidden button_new']")
    public WebControl closePopupButton;

    @FindBy(xpath = "//b[@class='gold_data']")
    public WebControl goldDataTextLabel;

    @FindBy(xpath = "//div[contains(@class,'char_stat')]")
    public WebControl lifeCaptcha;

    @FindBy(xpath = "//i[@class='life_perc']")
    public WebControl lifePerc;

    @FindBy(xpath = "//td[@id='potion_td_2']/span[@class='count_amount  count_amount']")
    public WebControl potionTD;

    @FindBy(xpath = "//div[@class='watch_attack_type']//*[@id='watch_find']")
    public WebControl watchfindButton;

    @FindBy(xpath = "//form[@id='attack_form']//*[@type='submit']")
    public WebControl attackButton;

    @FindBy(xpath = "//select[@name='auto_watch']/..//input[@type='submit']")
    public WebControl autoDozor;

    @FindBy(xpath = "//span[@class='energy_now']")
    public WebControl energyTextLabel;

    public GamePO(WebDriver driver) {
        super(driver);
    }
}
