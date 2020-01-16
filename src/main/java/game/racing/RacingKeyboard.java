package game.racing;

import game.GameKeyboard;

import java.util.Scanner;

import static constant.PrinterConstant.EXIT;

public class RacingKeyboard implements GameKeyboard {

    private final Scanner scanner;

    public RacingKeyboard(){
        this.scanner = new Scanner(System.in);
    }

    @Override
    public String listenCarNumber() {
        return listen();
    }

    @Override
    public String listenTryCount() {
        return listen();
    }

    @Override
    public boolean listenContinue() {
        return (!listen().equals(EXIT));
    }

    private String listen() {
        return scanner.nextLine();
    }
}
