package framework.decorator.element;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import java.util.*;

public class WebControl extends RemoteWebElement {
    private static final Logger LOGGER = Logger.getLogger(WebControl.class);
    private String name;
    private String page;
    private WebElement webElement;

    public WebControl(WebElement webElement, String name, String page) {
        this.webElement = webElement;
        this.name = name;
        this.page = page;
    }

    @Override
    public String toString() {
        return "WebControl{" + page + "." + name + "}";
    }

    @Override
    public void click() {
        LOGGER.info(toString() + ".click():START - " + clickAndLog());
    }

    private String clickAndLog() {
        Date start = new Date();
        try {
            webElement.click();
            return successMessage(start);
        } catch (Exception e) {
            return new Date().getTime() - start.getTime() + " ms - FAIL - Exception " + e.getLocalizedMessage().replaceAll("\\n", "");
        }
    }

    @Override
    public void submit() {
        LOGGER.info(toString() + ".submit():START");
        webElement.submit();
    }

    @Override
    public void sendKeys(CharSequence... keysToSend) {
        LOGGER.info(toString() + ".sendKeys(" + Arrays.toString(keysToSend) + "):START - " + sendKeysAndLog(keysToSend));
    }

    private String sendKeysAndLog(CharSequence... keysToSend) {
        Date start = new Date();
        try {
            webElement.sendKeys(keysToSend);
            return successMessage(start);
        } catch (Exception e) {
            if (e.getMessage().contains("Timed out receiving message from renderer")) {
                throw e;
            }
            return failMessage(start, e);
        }
    }

    @Override
    public void clear() {
        LOGGER.info(toString() + ".clear():START");
        webElement.clear();
    }

    @Override
    public String getTagName() {
        LOGGER.info(toString() + ".getTagName():START");
        return webElement.getTagName();
    }

    @Override
    public String getAttribute(String name) {
        Map<String, String> res = getAttributeAndLog(name);
        LOGGER.info(toString() + ".getAttribute(" + name + "):START - " + res.get("msg"));
        return res.get("res");
    }

    private Map<String, String> getAttributeAndLog(String name) {
        Map<String, String> res = new HashMap<>();
        Date start = new Date();
        try {
            String resString = webElement.getAttribute(name);
            res.put("msg", successMessage(start) + " res = " + resString);
            res.put("res", resString);
        } catch (Exception e) {
            if (e.getMessage().contains("Timed out receiving message from renderer")) {
                throw e;
            }
            res.put("msg", failMessage(start, e));
        }
        return res;
    }

    @Override
    public boolean isSelected() {
        LOGGER.info(toString() + ".isSelected():START");
        return webElement.isSelected();
    }

    @Override
    public boolean isEnabled() {
        LOGGER.info(toString() + ".isEnabled():START");
        return webElement.isEnabled();
    }

    @Override
    public String getText() {
        Map<String, String> res = getTextAndLog();
        LOGGER.info(toString() + ".getText():START - " + res.get("msg"));
        return res.get("res");
    }

    private Map<String, String> getTextAndLog() {
        Map<String, String> res = new HashMap<>();
        Date start = new Date();
        try {
            String text = webElement.getText();
            res.put("msg", successMessage(start) + " res = " + text);
            res.put("res", text);
        } catch (Exception e) {
            if (e.getMessage().contains("Timed out receiving message from renderer")) {
                throw e;
            }
            res.put("msg", failMessage(start, e));
        }
        return res;
    }

    @Override
    public List<WebElement> findElements(By by) {
        LOGGER.info(toString() + ".findElements():START");
        return webElement.findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        LOGGER.info(toString() + ".findElement():START");
        return webElement.findElement(by);
    }

    @Override
    public boolean isDisplayed() {
        Map<String, Object> res = isDisplayedAndLog();
        LOGGER.info(toString() + ".isDisplayed():START - " + res.get("msg"));
        return (res.get("res") != null) && (boolean) res.get("res");
    }

    private Map<String, Object> isDisplayedAndLog() {
        Map<String, Object> res = new HashMap<>();
        Date start = new Date();
        try {
            boolean isDisplayed = webElement.isDisplayed();
            res.put("msg", successMessage(start) + " res = " + isDisplayed);
            res.put("res", isDisplayed);
        } catch (Exception e) {
            if (e.getMessage().contains("Timed out receiving message from renderer")) {
                throw e;
            }
            res.put("msg", failMessage(start, e));
        }
        return res;
    }

    @Override
    public Point getLocation() {
        LOGGER.info(toString() + ".getLocation():START");
        return webElement.getLocation();
    }

    @Override
    public Dimension getSize() {
        LOGGER.info(toString() + ".getSize():START");
        return webElement.getSize();
    }

    @Override
    public String getCssValue(String propertyName) {
        LOGGER.info(toString() + ".getCssValue():START");
        return webElement.getCssValue(propertyName);
    }

    public boolean saveIsDisplayed() {
        Date start = new Date();
        try {
            return isDisplayed();
        } catch (Exception e) {
            if (e.getMessage().contains("Timed out receiving message from renderer")) {
                throw e;
            }
            LOGGER.info(toString() + ".saveIsDisplayed() - " + failMessage(start, e));
            return false;
        }
    }

    private String failMessage(Date start, Exception e) {
        return new Date().getTime() - start.getTime() + " ms - FAIL - Exception " + e.getLocalizedMessage().replaceAll("\\n", "");
    }

    private String successMessage(Date start) {
        return new Date().getTime() - start.getTime() + " ms - SUCCESS";
    }

    public int getTextAsInt() {
        String text = getText();
        try {
            return Integer.parseInt(text.replaceAll("\\D", ""));
        } catch (Exception e) {
            if (e.getMessage().contains("Timed out receiving message from renderer")) {
                throw e;
            }
            LOGGER.error(text + " have no digits");
            return 0;
        }
    }

    public void waitForMeDisplay() throws InterruptedException {
        Date start = new Date();
        while ((!isDisplayed()) && (new Date().getTime() - start.getTime() < 1000)) {
            Thread.sleep(100);
        }
    }


}

