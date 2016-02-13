package games.botva;

import framework.model.User;

public class BotvaBot {
    public static void main(String[] srg) throws Exception {
        new GameBO("http://g2.botva.ru/m.php#", new User("misha.mikus@gmail.com", "SilverSmith1590")).startGame(true);
    }
}
