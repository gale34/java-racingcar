import static spark.Spark.*;

import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.Map;

public class WebMain {

    public static void main(String[] args) throws InterruptedException {
        port(8080);
        get("/", (req, res) -> render(null,"/index.html"));
/*        String carNames = inputCarNames();
        Integer roundCount = inputRoundCount();

        RacingGame racingGame = new RacingGame(carNames, roundCount);
        RacingGameView racingGameView = new RacingGameConsoleView();

        registerRacingGameEvents(racingGameView);
        racingGameView.showGameProgressGuidanceMessage();
        racingGame.start();

        Events.reset();*/
    }

    public static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine()
                .render(new ModelAndView(model, templatePath));
    }

    /*private static void registerRacingGameEvents(RacingGameView racingGameView) {
        Events.handle((RoundOverEvent event) -> racingGameView.showCurrentPosition(event.getCarPositions()));
        Events.handle((GameOverEvent event) -> racingGameView.showGameResult(event.getWinners()));
    }*/
}
