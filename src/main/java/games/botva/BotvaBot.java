package games.botva;

import framework.Property;
import framework.model.User;

public class BotvaBot {
    public static void main(String[] srg) throws Exception {
        new GameBO(Property.get("BotvaBot.url"), new User(Property.get("BotvaBot.user"), Property.get("BotvaBot.pass"))).startGame(false);
    }
}
