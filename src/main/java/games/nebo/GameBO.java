package games.nebo;

import framework.Application;
import framework.decorator.element.WebControl;
import framework.model.User;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class GameBO {
    public String server;
    public User user;
    public GamePO gamePO;
    private Application application;
    private Map<String, String> report;

    public GameBO(String server, User user) {
        this.server = server;
        this.user = user;
        application = new Application();
    }

    public void startGame(boolean hide) throws Exception {
        while (true) {
            try {
                clearReporter();
            } catch (Exception e) {
            }
            try {
                startChrome(hide);
            } catch (Exception e) {
            }
            try {
                login();
            } catch (Exception e) {
            }
            try {
                lift();
            } catch (Exception e) {
            }
            try {
                merchandising();
            } catch (Exception e) {
            }
            try {
                buy();
            } catch (Exception e) {
            }
            try {
                money();
            } catch (Exception e) {
            }
            try {
                buy2();
            } catch (Exception e) {
            }
            try {
                quests();
            } catch (Exception e) {
            }
            try {
                doors();
            } catch (Exception e) {
            }
            try {
                humans();
            } catch (Exception e) {
            }
            try {
                stocked();
            } catch (Exception e) {
            }
            try {
                labelReport();
            } catch (Exception e) {
            }
            closeChrome();
            printReport();
        }
    }

    private void clearReporter() {
        report = new HashMap<>();
    }

    private String taskProgress() {
        int currentProgress = 0;
        int taskProgress = 0;
        try {
            currentProgress = Integer.parseInt(gamePO.questTaskCurrentProgressTextLabel.getText());
            taskProgress = Integer.parseInt(gamePO.questTaskValueTextLabel.getText());
        } catch (Exception ignored) {
        }
        if ((currentProgress > 0) && (taskProgress != 0))
            return Math.round(((double) currentProgress * 100) / (double) taskProgress) + "% done(" + currentProgress + "/" + taskProgress + "); leftTime = " + gamePO.questTaskTimeTextLabel.getText();
        return "";
    }

    private void labelReport() {
        try {
            application.get("http://nebo.mobi/home");
            report.put("gold(bucks)", Integer.parseInt(gamePO.bucksTextLabel.getText()) + "");
            report.put("iron(coin)", Integer.parseInt(gamePO.coinTextLabel.getText().replaceAll("'", "")) + "");
            report.put("receipts", gamePO.receiptsTextLabel.getText());
            String text = gamePO.questTaskTextLabel.getText();
            if (text != null) {
                report.put("task(text)", text);
                report.put("task(progress)", taskProgress());
            }
        } catch (Exception e) {
            report.put("exception", e.getMessage().replaceAll("\n", ""));
        }
    }


    private void printReport() {
        for (Map.Entry<String, String> entry : report.entrySet()) {
            System.out.println(entry.getKey() + "\t:\t" + entry.getValue());
        }
    }

    private void closeChrome() {
        application.stopChrome();
        report.put("stopDate", now());
    }

    private String now() {
        return new SimpleDateFormat("MM.DD.YYYY hh:mm:ss").format(new Date());
    }

    private void startChrome(boolean hide) throws Exception {
        report.put("startDate", now());
        application.startChrome(hide);
    }

    private void login() {
        application.get(server);
        gamePO = new GamePO(application.driver);
        gamePO.email.sendKeys(user.login);
        gamePO.password.sendKeys(user.pass);
        gamePO.submit.click();
    }

    private void stocked() {
        int counter = 0;
        int subCounter = 0;
        application.get("http://nebo.mobi/home");
        if (gamePO.stockedImg.isDisplayed()) {
            gamePO.stockedImg.click();
            counter++;
            while (gamePO.floorAction.isDisplayed()) {
                gamePO.floorAction.click();
                subCounter++;
            }
        }
        if (counter > 0)
            report.put("stocked", "counter = " + counter + " (actions) subCounter = " + subCounter + " (actions)");
    }

    private void humans() {
        int counter = 0;
        application.get("http://nebo.mobi/humans");
        while (gamePO.humanImg.isDisplayed()) {
            gamePO.humanImg.click();
            gamePO.evict.click();
            application.get("http://nebo.mobi/humans");
            counter++;
        }
        if (counter > 0)
            report.put("humans", counter + " (fired)");
    }

    private void doors() {
        int counter = 0;
        application.get("http://nebo.mobi/doors");
        int keys = getKeyCount();
        if (keys > 0)
            report.put("keys", keys + "");
        while (getKeyCount() > 0) {
            gamePO.firstDoor.click();
            application.get("http://nebo.mobi/doors");
            counter++;
        }
        if (counter > 0)
            report.put("doors", counter + " (actions)");
    }

    private int getKeyCount() {
        try {
            return Integer.parseInt(gamePO.keyCountTextLabel.getText());
        } catch (Exception e) {
            return 0;
        }
    }

    private void quests() {
        int counter = 0;
        application.get("http://nebo.mobi/quests");
        while (gamePO.getAwardButton.isDisplayed()) {
            gamePO.getAwardButton.click();
            counter++;
        }
        if (counter > 0)
            report.put("quests", counter + " (actions)");
    }

    private void buy2() {
        int counter = 0;
        int subCounter = 0;
        application.get("http://nebo.mobi/home");
        while (gamePO.buyImg.isDisplayed()) {
            gamePO.buyImg.click();
            counter++;
            while (gamePO.buy2.isDisplayed()) {
                buyBlock(gamePO.buy2);
                subCounter++;
            }
        }
        if (counter > 0)
            report.put("buy2", "counter = " + counter + " (actions) subCounter = " + subCounter + " (actions)");
    }

    private void buyBlock(WebControl buyControl) {
        buyControl.click();
        gamePO.buyC.click();
        gamePO.buyB.click();
        gamePO.buyA.click();
    }

    private void money() {
        int counter = 0;
        application.get("http://nebo.mobi/home");
        gamePO.moneyImg.click();
        while (gamePO.floorAction.isDisplayed()) {
            gamePO.floorAction.click();
            counter++;
        }
        if (counter > 0)
            report.put("money", counter + " (actions)");
    }

    private void buy() {
        int counter = 0;
        application.get("http://nebo.mobi/home");
        while (gamePO.buy.isDisplayed()) {
            buyBlock(gamePO.buy);
            counter++;
        }
        if (counter > 0)
            report.put("buy", counter + " (actions)");
    }

    private void merchandising() {
        int counter = 0;
        application.get("http://nebo.mobi/home");
        while (gamePO.tovar.isDisplayed()) {
            gamePO.tovar.click();
            counter++;
        }
        if (counter > 0)
            report.put("merchandising", counter + " (actions)");
    }

    private void lift() {
        int counter = 0;
        application.get("http://nebo.mobi/lift");
        while (gamePO.liftState.isDisplayed()) {
            gamePO.liftState.click();
            counter++;
        }

        if (counter > 0)
            report.put("lift", counter + " (actions)");
    }


}
