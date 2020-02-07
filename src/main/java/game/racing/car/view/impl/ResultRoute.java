package game.racing.car.view.impl;

import game.racing.car.event.Events;
import game.racing.car.event.GameOverEvent;
import game.racing.car.event.RoundOverEvent;
import game.racing.car.model.dto.CarPosition;
import game.racing.car.model.dto.RacingRoundResult;
import game.racing.car.service.RacingGame;
import game.racing.car.service.RacingGameService;
import game.racing.car.service.impl.RacingGameWebService;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static game.racing.car.utils.RacingGameUtil.convertToCarWebPosition;
import static game.racing.car.view.WebConsoleView.render;

public class ResultRoute {

    private ResultRoute() {
    }

    public static class post implements Route {

        private static final String CAR_NAMES_PARAMETER = "names";
        private static final String ROUND_COUNT_REQUEST_PARAMETER = "turn";

        private static final String GAME_RESULT_PAGE = "/result.html";

        @Override
        public Object handle(Request request, Response response) {
            Integer roundCount = Integer.valueOf(request.queryParams(ROUND_COUNT_REQUEST_PARAMETER));
            String[] carNames = request.session().attribute(CAR_NAMES_PARAMETER);

            RacingGame racingGame = new RacingGame(carNames, roundCount);
            RacingGameService racingGameService = new RacingGameWebService(racingGame);
            Map<String, Object> model = racingGameService.run();
            return render(model, GAME_RESULT_PAGE);
        }


    }
}
