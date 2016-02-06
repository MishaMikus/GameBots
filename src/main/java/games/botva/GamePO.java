package games.botva;

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

    @FindBy(xpath = "//form[@action='harbour.php?a=pier']/*[@type='submit']")
    public WebControl pierButton;

    @FindBy(xpath = "//div[@class='watch_attack_type']//*[@id='watch_find']")
    public WebControl watchfindButton;

    @FindBy(xpath = "//form[@id='attack_form']//*[@type='submit']")
    public WebControl attackButton;

    @FindBy(xpath = "//form[@id='form_zorro_1']/div[@class='watch_attack_type']//*[@type='submit']")
    public WebControl zorroButton;

    @FindBy(xpath = "//form[@action='?a=monster']/*[@type='submit']")
    public WebControl monsterButton;

    @FindBy(xpath = "//div[@class='school_history_lever ']")
    public WebControl historyButton;

    @FindBy(xpath = "//b[@id='school_history_fun']")
    public WebControl funTextLabel;

    @FindBy(xpath = "//*[@id='school_history_shop']")
    public WebControl schoolHistoryShop;

    @FindBy(xpath = "(//form[@action='history.php?a=shop']/div[@class='button_new small_sl'])[1]")
    public WebControl schoolHistoryShopByButton;

    @FindBy(xpath = "(//form[@action='history.php?a=shop&type=open']/div[@class='button_new small_sl'])[1]")
    public WebControl schoolHistoryShopOpen;

    @FindBy(xpath = "//a[contains(@href,'do_cmd=get_all_prizes')]")
    public WebControl getPriceButton;

    @FindBy(xpath = "//a[contains(@href,'do_cmd=search')]")
    public WebControl findArenaButton;

    @FindBy(xpath = "//a[@class='arena_enemy ']/div[3]/div[1]")
    public List<WebControl> enemyList;

    @FindBy(xpath = "//form[@action='?a=attack']/input[@type='submit']")
    public WebControl attackMonsterButton;

    @FindBy(xpath = "//img[@src='http://i.botva.ru/images/items/ny_carts.jpg']")
    public WebControl saniButton;

    @FindBy(id = "button1_2")
    public WebControl shareButton;

    @FindBy(xpath = "//select[@name='auto_watch']/..//input[@type='submit']")
    public WebControl autoDozor;

    @FindBy(xpath = "//form[@id='mine_form']//input[@type='submit']")
    public WebControl autoMine;

    @FindBy(xpath = "//a[contains(@href,'m=dig')]")
    public WebControl doMine;

    @FindBy(xpath = "//div[@id='flyings']/div")
    public List<WebControl> eggButton;

    @FindBy(xpath = "//span[@rel='heal']")
    public WebControl healButton;

    @FindBy(xpath = "//span[@rel='feed']")
    public WebControl feedButton;

    @FindBy(xpath = "//center[contains(text(),'%')]")
    public WebControl healZooTextLabel;

    @FindBy(xpath = "//form/input[@name='do_cmd'][@value='heal']/../input[@type='submit']")
    public WebControl doHealButton;

    @FindBy(xpath = "//form/input[@name='do_cmd'][@value='clean']/../input[@type='submit']")
    public WebControl doCleanButton;

    @FindBy(xpath = "//*[@id='flying_block']/center/b")
    public WebControl cleanTextLabel;

    @FindBy(xpath = "//div[@id='flyings']/div")
    public WebControl egg;

    @FindBy(xpath = "//a[contains(@href,'m=random')]")
    public WebControl randomPolyana;

    @FindBy(xpath = "//a[contains(@href,'m=restart')]")
    public WebControl restartPolyana;

    @FindBy(xpath = "//a[contains(@href,'&m=start&t=1')]")
    public WebControl smallPolyana;

    @FindBy(xpath = "//a[contains(@href,'&m=start&t=2')]")
    public WebControl bigPolyana;

    public GamePO(WebDriver driver) {
        super(driver);
    }
}
