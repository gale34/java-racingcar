package game.racing.car.view.impl;

import game.racing.car.event.Events;
import game.racing.car.event.GameOverEvent;
import game.racing.car.event.RoundOverEvent;
import game.racing.car.model.dto.CarPosition;
import game.racing.car.model.dto.RacingRoundResult;
import game.racing.car.service.RacingGame;
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
        private static final String GAME_RESULT_WINNER_PARAMETER = "winners";
        private static final String GAME_RESULT_ROUND_PARAMETER = "round";

        private static final String GAME_RESULT_PAGE = "/result.html";

        @Override
        public Object handle(Request request, Response response) {
            Integer roundCount = Integer.valueOf(request.queryParams(ROUND_COUNT_REQUEST_PARAMETER));
            String[] carNames = request.session().attribute(CAR_NAMES_PARAMETER);

            Map<String, Object> model = new HashMap<>();
            registerRacingGameEvents(model);

            RacingGame racingGame = new RacingGame(carNames, roundCount);
            racingGame.start();

            while (!RacingGameComplete(model)) {
                // 레이싱 게임이 끝날때까지 대기.
            }
            return render(model, GAME_RESULT_PAGE);
        }

        private static void registerRacingGameEvents(Map<String, Object> model) {
            Events.handle((RoundOverEvent event) -> saveCurrentPosition(model, event.getCarPositions()));
            Events.handle((GameOverEvent event) -> saveGameResult(model, event.getWinners()));
        }

        private static void saveCurrentPosition(Map<String, Object> model, List<CarPosition> carPositions) {
            List<RacingRoundResult> roundCarPositions = new ArrayList<>();
            if (model.containsKey(GAME_RESULT_ROUND_PARAMETER)) {
                roundCarPositions = (List<RacingRoundResult>) model.get(GAME_RESULT_ROUND_PARAMETER);
            }

            roundCarPositions.add(new RacingRoundResult(roundCarPositions.size() + 1, convertToCarWebPosition(carPositions)));
            model.put(GAME_RESULT_ROUND_PARAMETER, roundCarPositions);
        }

        private static void saveGameResult(Map<String, Object> model, List<String> winners) {
            model.put(GAME_RESULT_WINNER_PARAMETER, winners);
        }

        private static Boolean RacingGameComplete(Map<String, Object> model) {
            return model.containsKey(GAME_RESULT_WINNER_PARAMETER);
        }

    }
}
