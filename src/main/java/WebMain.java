import game.racing.car.event.Events;
import game.racing.car.model.RacingGameFactory;
import game.racing.car.service.RacingGameWebRequestHandler;

import java.util.Map;

import static game.racing.car.view.WebConsoleView.*;
import static spark.Spark.*;

public class WebMain {

    public static void main(String[] args) {
        port(8080);

        RacingGameFactory racingGameFactory = new RacingGameFactory();

        get("/", (req, res) -> renderIndexPage());

        post("/name", (req, res) -> {
            Map<String, Object> model = RacingGameWebRequestHandler.handleNameRequest(req, racingGameFactory);
            return renderGameStartPage(model);
        });

        post("/result", (req, res) -> {
            Map<String, Object> model = RacingGameWebRequestHandler.handleResultRequest(req, racingGameFactory);
            return renderGameResultPage(model);
        });

        Events.reset();
    }
}
