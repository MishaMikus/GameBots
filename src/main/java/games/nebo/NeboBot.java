package games.nebo;

import framework.model.User;

public class NeboBot {
    public static void main(String[] srg) throws Exception {
       new GameBO("http://nebo.mobi/login",new User("silversmith", "Student1")).startGame(true);
    }
}
