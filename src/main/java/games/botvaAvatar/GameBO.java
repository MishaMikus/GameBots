package games.botvaAvatar;

import framework.Application;
import framework.model.User;

public class GameBO {
    public User botvaUser;
    public GamePO gamePO;
    private Application application = new Application();

    public GameBO(User botvaUser) {
        this.botvaUser = botvaUser;
    }

    public void startGame(boolean hidden) throws Exception {
        while (true) {
            login(hidden);
            try {
                watchfind();
                dozor();
                System.out.println();
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
}
