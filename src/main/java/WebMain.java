import static game.racing.car.view.WebConsoleView.renderGameStartPage;
import static game.racing.car.view.WebConsoleView.renderIndexPage;
import static spark.Spark.*;

import game.racing.car.event.Events;
import game.racing.car.event.GameOverEvent;
import game.racing.car.service.RacingGame;
import game.racing.car.view.RacingGameView;
import game.racing.car.view.impl.WebGameVIew;

public class WebMain {

    public static void main(String[] args) throws InterruptedException {
        port(8080);

        registerRacingGameEvents(new WebGameVIew());

        get("/", (req, res) -> renderIndexPage());

        post("/name", (req, res) -> renderGameStartPage(req));

        post("/result", (req, res) -> {
            Integer round = Integer.valueOf(req.queryParams("turn"));

            RacingGame racingGame = new RacingGame()
        });

        Events.reset();


/*        String carNames = inputCarNames();
        Integer roundCount = inputRoundCount();

        RacingGame racingGame = new RacingGame(carNames, roundCount);
        RacingGameView racingGameView = new RacingGameConsoleView();

        registerRacingGameEvents(racingGameView);
        racingGameView.showGameProgressGuidanceMessage();
        racingGame.start();

        Events.reset();*/
    }

    private static void registerRacingGameEvents(RacingGameView racingGameView) {
        Events.handle((GameOverEvent event) -> racingGameView.showGameResult(event.getWinners()));
    }
}
