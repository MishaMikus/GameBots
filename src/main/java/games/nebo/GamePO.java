package games.nebo;

import framework.Page;
import framework.decorator.element.WebControl;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class GamePO extends Page {
    @FindBy(xpath = "//input[@name='login']")
    public WebControl email;

    @FindBy(xpath = "//input[@name='password']")
    public WebControl password;

    @FindBy(xpath = "//input[@name=':submit']")
    public WebControl submit;

    @FindBy(xpath = "//a[@class='tdu'][contains(@href,'liftState')]")
    public WebControl liftState;

    @FindBy(xpath = "//a[@class='tdu'][contains(@href,'floorPanel:state:action')][not(contains(@href,'floorPanel:state:action'))]")
    public WebControl tovar;

    @FindBy(xpath = "//a[@class='tdu'][starts-with(@href,'floor')][not(contains(@href,'majorFloorsBlock:majorFloorsPanel'))]")
    public WebControl buy;

    @FindBy(xpath = "//a[@class='tdu'][starts-with(@href,'../../floor')][not(contains(@href,'logoutLink'))]")
    public WebControl buy2;

    @FindBy(xpath = "//a[@class='tdu'][contains(@href,':productA:')]")
    public WebControl buyA;

    @FindBy(xpath = "//a[@class='tdu'][contains(@href,':productB:')]")
    public WebControl buyB;

    @FindBy(xpath = "//a[@class='tdu'][contains(@href,':productC:')]")
    public WebControl buyC;

    @FindBy(xpath = "//img[@src='/images/icons/tb_sold.png']")
    public WebControl moneyImg;

    @FindBy(xpath = "//a[@class='tdu'][contains(@href,':floorPanel:state:action::ILinkListener::')]")
    public WebControl floorAction;

    @FindBy(xpath = "//img[@src='/images/icons/tb_empty.png']")
    public WebControl buyImg;

    @FindBy(xpath = "//a[@class='btng btn60'][contains(@href,'quest:getAwarLink::ILinkListener::')]")
    public WebControl getAwardButton;

    @FindBy(xpath = "//img[@src='/images/icons/key.png']/../span")
    public WebControl keyCountTextLabel;

    @FindBy(xpath = "//img[@src='/images/icons/doors/door_closed.png']")
    public WebControl firstDoor;

    @FindBy(xpath = "//b[@class='abstr']/span[not(text()='9')]/../../a")
    public WebControl humanImg;

    @FindBy(xpath = "//a[@class='btnr'][contains(@href,':evictLink::ILinkListener::')]")
    public WebControl evict;

    @FindBy(xpath = "//img[@src='/images/icons/tb_stocked.png']")
    public WebControl stockedImg;

    @FindBy(xpath="//img[@src='/images/icons/mn_iron.png']/../span")
    public WebControl coinTextLabel;

    @FindBy(xpath="//img[@src='/images/icons/mn_gold.png']/../span")
    public WebControl bucksTextLabel;

    @FindBy(xpath="//a[@href='city/quests']/span")
    public WebControl questTaskTextLabel;

    @FindBy(xpath="//a[@href='city/quests']/../div[@class='small minor nshd']/span[1]/span[1]")
    public WebControl questTaskCurrentProgressTextLabel;

    @FindBy(xpath="//a[@href='city/quests']/../div[@class='small minor nshd']/span[1]/span[2]")
    public WebControl questTaskValueTextLabel;

    @FindBy(xpath="//a[@href='city/quests']/../div[@class='small minor nshd']/span[2]")
    public WebControl questTaskTimeTextLabel;

    @FindBy(xpath="//span[@class='amount nwr']/span")
    public WebControl receiptsTextLabel;

    public GamePO(WebDriver driver) {
        super(driver);
    }

}
