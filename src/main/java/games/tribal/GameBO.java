package games.tribal;

import framework.Application;
import framework.decorator.element.WebControl;
import framework.model.User;
import org.apache.log4j.Logger;

import java.util.Date;

public class GameBO {
    private static final Logger LOGGER = Logger.getLogger(GameBO.class);
    private String MAIN_PAGE_LANDING = "https://www.voyna-plemyon.ru/";
    public GamePO gamePO;
    private Application application = new Application();
    private static Date start;
    private static User user;

    public GameBO(User user) {
        this.user = user;
    }

    private String lastWorld = null;
    private String currentServer = null;

    public void startGame(boolean hidden) throws Exception {
        while (true) {
            application.startChrome(hidden);
            start = new Date();
            login();
            loginNextWorld();
            build();
            application.stopChrome();
        }
    }

    private void build() throws InterruptedException {
        application.get(currentServer + "?screen=main");
        application.get(gamePO.buildButton.getAttribute("href"));
    }

    private void loginNextWorld() throws InterruptedException {
        gamePO.worldButton.waitForMeDisplay();
        int worldIndex = 0;
        for (WebControl worldButton : gamePO.worldButtonList) {
            worldIndex++;
            if (worldButton.getText().equals(lastWorld)) {
                break;
            }
        }
        if (worldIndex >= gamePO.worldButtonList.size()) {
            lastWorld = gamePO.worldButtonList.get(0).getText();
            gamePO.worldButtonList.get(0).click();
        } else {
            lastWorld = gamePO.worldButtonList.get(worldIndex).getText();
            gamePO.worldButtonList.get(worldIndex).click();
        }

        currentServer = application.driver.getCurrentUrl().split("game\\.php")[0] + "game.php";
    }

    private void login() {
        application.get(MAIN_PAGE_LANDING);
        gamePO = new GamePO(application.driver);
        gamePO.loginInput.sendKeys(user.login);
        gamePO.passwordInput.sendKeys(user.pass);
        gamePO.submitButton.click();
    }

}
