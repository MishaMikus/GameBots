package games.botva;

import framework.Application;
import framework.decorator.element.WebControl;
import framework.model.User;
import org.apache.log4j.Logger;

import java.util.Date;

public class GameBO {
    private static final Logger LOGGER = Logger.getLogger(GameBO.class);
    private static final long SESSION_TIMEOUT_MS = 60*60 * 1000;
    public String botvaServer;
    public User botvaUser;
    public GamePO gamePO;
    private Application application = new Application();
    private static Date start;

    public GameBO(String botvaServer, User botvaUser) {
        this.botvaServer = botvaServer;
        this.botvaUser = botvaUser;
    }

    public void startGame(boolean hidden) throws Exception {
        while (true) {
            login(hidden);
            try {
                zoo();
                zooDozor();
                doMine();
                pier();
                watchfind();
                zorro();
                monster();
                history();
                getPrice();
                arena();
                sani();
                dozor();
                minePolyana();
                mine();

                eat();

            } catch (Exception ignored) {
                close();
            }
            close();
        }
    }

    private void zooDozor() {
        try {
            switchGameFrame("castle.php?a=zoo&id=6");
            switchGameFrame("castle.php?a=zoo&id=6");
            gamePO.egg.waitForMeDisplay();
            for (int i = 0; i < 3; i++) {
                gamePO.eggButton.get(i).click();

                gamePO.feedButton.click();
                gamePO.feedZooTextLabel.waitForMeDisplay();
                String feedZooLevel = gamePO.feedZooTextLabel.getText().split("%")[0];
                if (Integer.parseInt(feedZooLevel) < 70) {
                    gamePO.doFeedButton.click();
                }
                gamePO.eventsButton.click();
                gamePO.doDozorButton.click();
                gamePO.doBigDozorButton.click();
            }
        } catch (Exception e) {
        }
    }

    private void minePolyana() {
        switchGameFrame("mine.php?a=mine");
        gamePO.smallPolyana.click();
        while (gamePO.randomPolyana.isDisplayed()) {
            gamePO.randomPolyana.click();
            gamePO.restartPolyana.click();
        }
        switchGameFrame("mine.php?a=mine");

        gamePO.bigPolyana.click();
        while (gamePO.randomPolyana.isDisplayed()) {
            gamePO.randomPolyana.click();
            gamePO.restartPolyana.click();
        }
    }

    private void zoo() throws InterruptedException {
        try {
            switchGameFrame("castle.php?a=zoo&id=6");
            switchGameFrame("castle.php?a=zoo&id=6");
            gamePO.egg.waitForMeDisplay();
            for (int i = 0; i < 3; i++) {
                gamePO.eggButton.get(i).click();
                gamePO.healButton.click();
                gamePO.healZooTextLabel.waitForMeDisplay();
                String healZooLevel = gamePO.healZooTextLabel.getText().split("%")[0];
                if (Integer.parseInt(healZooLevel) < 70) {
                    gamePO.doHealButton.click();
                }
                gamePO.feedButton.click();
                gamePO.cleanTextLabel.waitForMeDisplay();
                String cleanZooLevel = gamePO.cleanTextLabel.getText().split(" /")[0];
                if (Integer.parseInt(cleanZooLevel) < 4) {
                    gamePO.doCleanButton.click();
                }
            }
        } catch (Exception e) {
        }
    }

    private void doMine() {
        switchGameFrame("mine.php?a=open");
        gamePO.doMine.click();
    }

    private void switchGameFrame(String url) {
        application.get(botvaServer + url);
        application.switchToFrame("iframe_game");
    }

    private void mine() {
        switchGameFrame("mine.php?a=open");
        gamePO.autoMine.click();
    }

    private void dozor() {
        switchGameFrame("dozor.php");
        gamePO.autoDozor.click();
    }

    private void sani() throws InterruptedException {
        start = new Date();
        switchGameFrame("index.php");
        if (gamePO.saniButton.isDisplayed()) {
            gamePO.saniButton.click();
            Thread.sleep(3 * 1000);
            while (gamePO.shareButton.isDisplayed()) {
                application.executeJavascript("javascript:loadUsers(1)");
                for (int i = 0; i < 6; i++) {
                    gamePO.shareButton.click();
                }
            }
        }
    }

    private void arena() {
        switchGameFrame("dozor.php");
        gamePO.findArenaButton.click();

        if (getEnemyListSize() > 0) {
            WebControl bestEnemy = gamePO.enemyList.get(0);
            System.out.println(gamePO.enemyList.get(0).getText());
            for (WebControl enemy : gamePO.enemyList) {
                if (enemy.getTextAsInt() < bestEnemy.getTextAsInt()) {
                    bestEnemy = enemy;
                }
            }
            bestEnemy.click();
            System.out.println(bestEnemy.getText());
        }
    }

    private int getEnemyListSize() {
        try {
            return gamePO.enemyList.size();
        } catch (Exception e) {
            return 0;
        }
    }

    private void getPrice() {
        switchGameFrame("school.php?m=class3");
        gamePO.getPriceButton.click();
    }

    private void history() throws InterruptedException {
        switchGameFrame("history.php");
        for (int i = 0; i < 10; i++) {
            gamePO.historyButton.click();
        }

        try {
            int pigs = gamePO.funTextLabel.getTextAsInt();
            if (pigs >= 2) {
                gamePO.schoolHistoryShop.click();
                gamePO.schoolHistoryShopByButton.click();
                switchGameFrame("history.php?a=shop&type=open");
                gamePO.schoolHistoryShopOpen.click();
            }
        } catch (Exception e) {
        }
    }

    private void monster() {
        switchGameFrame("dozor.php");
        gamePO.monsterButton.click();
        gamePO.attackMonsterButton.click();
    }

    private void zorro() {
        switchGameFrame("dozor.php");
        gamePO.zorroButton.click();
        gamePO.attackButton.click();
    }

    private void watchfind() {
        switchGameFrame("dozor.php");
        gamePO.watchfindButton.click();
        gamePO.attackButton.click();
    }

    private void pier() throws InterruptedException {
        switchGameFrame("harbour.php?a=pier");
        gamePO.pierButton.click();
    }

    private void close() {
        application.stopChrome();
    }

    private void login(boolean hidden) throws Exception {
        try {
            application.startChrome(hidden);
            start = new Date();
            application.get(botvaServer);
            gamePO = new GamePO(application.driver);
            gamePO.server.click();
            gamePO.email.sendKeys(botvaUser.login);
            gamePO.password.sendKeys(botvaUser.pass);
            gamePO.submit.click();
            application.switchToFrame("iframe_game");
            gamePO.lPopupClose.click();
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
            return Integer.parseInt(lifeInfo.split("\\|")[3].replaceAll("\\.", "").replaceAll("К", "000"));
        } else {
            return 0;
        }
    }

    private double getCurrentLife(String lifeInfo) {
        if (lifeInfo != null) {
            return Integer.parseInt(lifeInfo.split("\\|")[1].replaceAll("\\.", "").replaceAll("К", "000"));
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
