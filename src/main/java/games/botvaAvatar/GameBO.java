package games.botvaAvatar;

import framework.Application;
import framework.model.User;
import org.apache.log4j.Logger;

import java.util.Date;

public class GameBO {
    private static final Logger LOGGER = Logger.getLogger(GameBO.class);
    private static final long SESSION_TIMEOUT_MS = 10 * 1000;
    public User botvaUser;
    public GamePO gamePO;
    private Application application = new Application();
    private static Date start;

    public GameBO(User botvaUser) {
        this.botvaUser = botvaUser;
    }

    public void startGame(boolean hidden) throws Exception {
        while (true) {
            login(hidden);
            try {
                watchfind();
                dozor();
                eat();
            } catch (Exception ignored) {
                close();
            }
            close();
        }
    }


    private void switchGameFrame(String url) {
        application.get("http://avatar.botva.ru/m.php#" + url);
        application.switchToFrame("iframe_game");
    }

    private void dozor() {
        switchGameFrame("dozor.php");
        gamePO.autoDozor.click();
    }

    private void watchfind() throws InterruptedException {
        gamePO.energyTextLabel.waitForMeDisplay();
        while (gamePO.energyTextLabel.getTextAsInt() >= 25) {
            switchGameFrame("dozor.php");
            gamePO.watchfindButton.click();
            gamePO.attackButton.click();
        }
    }

    private void close() {
        application.stopChrome();
    }

    private void login(boolean hidden) throws Exception {
        try {
            application.startChrome(hidden);
            start = new Date();
            application.get("http://g2.botva.ru/m.php#");
            gamePO = new GamePO(application.driver);
            gamePO.server.click();
            gamePO.email.sendKeys(botvaUser.login);
            gamePO.password.sendKeys(botvaUser.pass);
            gamePO.submit.click();
            application.switchToFrame("iframe_game");
            gamePO.lPopupClose.click();
            application.get("http://g2.botva.ru/avatara.php?a=jump");
            application.switchToFrame("iframe_game");
        } catch (Exception e) {
        }
    }

    public void eat() throws InterruptedException {
        int lifePerc = getLife();
        if (lifePerc == 100) {
            LOGGER.info("getLife() = " + lifePerc + " its max");
            return;
        }
        while ((lifePerc < 100) && timeoutCondition()) {
            if (!gamePO.fieldPotion21.saveIsDisplayed()) {
                application.executeJavascript("doPotions()");
            }
            doEat();
            try {
                application.executeJavascript("doBuyPotionEx(2, true, 1)");
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                String lifeInfo = gamePO.lifeCaptcha.getAttribute("onmouseover");
                double percentD = (getCurrentLife(lifeInfo) / getMaxLife(lifeInfo)) * 100;
                LOGGER.info(""
                        + "\tlife = " + percentD + " %"
                        + "\tgold = " + getGoldData()
                );
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        start = new Date();
        gamePO.closePopupButton.click();
    }

    private boolean timeoutCondition() {
        return SESSION_TIMEOUT_MS > (new Date().getTime() - start.getTime());
    }

    private String getGoldData() {
        return gamePO.goldDataTextLabel.getText();
    }

    private double getMaxLife(String lifeInfo) {
        if (lifeInfo != null) {
            return Integer.parseInt(lifeInfo.split("\\|")[3].replaceAll("\\.","").replaceAll("К","000"));
        } else {
            return 0;
        }
    }

    private double getCurrentLife(String lifeInfo) {
        if (lifeInfo != null) {
            return Integer.parseInt(lifeInfo.split("\\|")[1].replaceAll("\\.","").replaceAll("К","000"));
        } else {
            return 0;
        }
    }

    private int getLife() {
        int res = 0;
        try {
            String atr = gamePO.lifePerc.getAttribute("data-life");
            if (atr != null) {
                res = Integer.parseInt(atr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    private void doEat() throws InterruptedException {
        while (getBluePotions() > 3) {
            application.executeJavascript("doDrinkEx(2, true)");
        }
    }


    public int getBluePotions() {
        int res = 0;
        try {
            res = Integer.parseInt(gamePO.potionTD.getText());
        } catch (Exception ignored) {
        }
        return res;
    }


}
