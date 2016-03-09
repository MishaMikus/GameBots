package games.botvaAvatar;

import framework.Property;
import framework.model.User;

public class AvatarBot {
    public static void main(String[] srg) throws Exception {
        new GameBO(new User(Property.get("BotvaBot.user"), Property.get("BotvaBot.pass"))).startGame(false);
    }
}
