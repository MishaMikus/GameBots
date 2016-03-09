package games.tribal;

import framework.Property;
import framework.model.User;

public class TribalBot {
    public static void main(String[] srg) throws Exception {
        new GameBO(new User(Property.get("TribalBot.user"),Property.get("TribalBot.pass"))).startGame(true);
    }
}
