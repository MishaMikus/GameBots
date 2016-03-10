package games.nebo;

import framework.Property;
import framework.model.User;

import java.util.Arrays;

public class NeboBot {
    public static void main(String[] arg) throws Exception {
        String url;
        String user;
        String pass;
        System.out.println("START [===============================] "+Arrays.toString(arg));
        if (arg.length==3){
            url=arg[0];
            user=arg[1];
            pass=arg[2];
        }else{
            url=Property.get("NeboBot.url");
            user=Property.get("NeboBot.user");
            pass=Property.get("NeboBot.pass");
        }
       new GameBO(url,new User(user,pass)).startGame(false);
    }
}
