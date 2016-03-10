package games.botva;

import framework.Property;
import framework.model.User;

public class BotvaBot {
    public static void main(String[] arg) throws Exception {
        String url;
        String user;
        String pass;
        if (arg.length==3){
            url=arg[0];
            user=arg[1];
            pass=arg[2];
        }else{
            url=Property.get("BotvaBot.url");
            user=Property.get("BotvaBot.user");
            pass=Property.get("BotvaBot.pass");
        }
        new GameBO(url, new User(user, pass)).startGame(false);
    }
}
