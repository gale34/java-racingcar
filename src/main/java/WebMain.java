import game.racing.car.event.Events;
import game.racing.car.service.RacingGameFactory;
import game.racing.car.service.RacingGameWebRequestHandler;

import java.util.Map;

import static game.racing.car.service.RacingGameWebRequestHandler.handleNameRequest;
import static game.racing.car.service.RacingGameWebRequestHandler.handleResultRequest;
import static game.racing.car.view.WebConsoleView.*;
import static spark.Spark.*;

public class WebMain {
    private static final String CAR_NAMES_REQUEST_PARAMETER = "names";
    private static final String ROUND_COUNT_REQUEST_PARAMETER = "turn";


    public static void main(String[] args) {
        port(8080);
        RacingGameFactory racingGameFactory = new RacingGameFactory();

        get("/", (req, res) -> renderIndexPage());

        post("/name", (req, res) -> {
            Map<String, Object> model = handleNameRequest(req.queryParams(CAR_NAMES_REQUEST_PARAMETER), racingGameFactory);
            return renderGameStartPage(model);
        });

        post("/result", (req, res) -> {
            Map<String, Object> model = handleResultRequest(req.queryParams(ROUND_COUNT_REQUEST_PARAMETER), racingGameFactory);
            return renderGameResultPage(model);
        });

        Events.reset();
    }
}
