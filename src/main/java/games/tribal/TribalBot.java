package games.tribal;

import framework.model.User;

public class TribalBot {
    public static void main(String[] srg) throws Exception {
        new GameBO(new User("SilverSmith", "Student1")).startGame(false);
    }
}
