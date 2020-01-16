package game.racing;

import game.GameConsole;

import static constant.PrinterConstant.*;

public class RacingConsole implements GameConsole {

    @Override
    public String askCarNumber() {
        return CAR_NUMBER_PRINT;
    }

    @Override
    public String askTryCount() {
        return TRY_COUNT_PRINT;
    }

    @Override
    public String askContinue() {
        return ASKING_CONTINUE_PRINT;
    }

    @Override
    public String printResult() {
        return PROCESS_RESULT;
    }
}
