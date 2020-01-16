package launcher;

import game.Game;
import game.racing.RacingGame;

public class Launcher {

    public static void main(String[]args) {

        Game game = RacingGame.create();
        game.start();
    }
}
