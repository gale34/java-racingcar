package game.racing;

import game.Game;
import game.GameConsole;
import game.GameKeyboard;

public class RacingGame implements Game {

    private static GameConsole console = null;
    private static GameKeyboard keyboard = null;

    static{
        console = new RacingConsole();
        keyboard = new RacingKeyboard();
    }

    public RacingGame(){}

    public static RacingGame create() {
        return new RacingGame();
    }

    @Override
    public void start() {

        boolean isContinue = true;

        while(isContinue) {

            System.out.println(console.askCarNumber());
            final int number = toInt(keyboard.listenCarNumber());

            System.out.println(console.askTryCount());
            final int tryCount = toInt(keyboard.listenTryCount());

            final RacingCars racingCars = new RacingCars(number, tryCount);

            System.out.println(console.printResult());
            racingCars.go();
            racingCars.trackedPrint();

            System.out.println(console.askContinue());
            isContinue = keyboard.listenContinue();
        }// while
    }// start(){}

    private int toInt(final String input) {
        return Integer.parseInt(input);
    }
}
