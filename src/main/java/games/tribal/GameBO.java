package games.tribal;

import framework.Application;
import framework.model.User;
import org.apache.log4j.Logger;

import java.util.Date;

public class GameBO {
    private static final Logger LOGGER = Logger.getLogger(GameBO.class);
    public GamePO gamePO;
    private Application application = new Application();
    private static Date start;
    private static User user;

    public GameBO(User user) {
        this.user = user;
    }

    public void startGame(boolean hidden) throws Exception {
        while (true) {
            application.startChrome(hidden);
            start = new Date();
            //TODO
            application.stopChrome();
        }
    }

}
