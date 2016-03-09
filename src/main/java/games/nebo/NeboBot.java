package games.nebo;

import framework.Property;
import framework.model.User;

public class NeboBot {
    public static void main(String[] srg) throws Exception {
       new GameBO(Property.get("NeboBot.url"),new User(Property.get("NeboBot.user"),Property.get("NeboBot.pass"))).startGame(false);
    }
}
