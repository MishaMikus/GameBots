package games.botvaAvatar;

import framework.model.User;

public class AvatarBot {
    public static void main(String[] srg) throws Exception {
        new GameBO(new User("misha.mikus@gmail.com", "SilverSmith1590")).startGame(false);
    }
}
