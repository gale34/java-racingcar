package game.racing.car.service;

import game.racing.car.event.Events;
import game.racing.car.event.GameOverEvent;
import game.racing.car.event.RoundOverEvent;
import game.racing.car.model.RacingGameFactory;
import game.racing.car.model.dto.CarPosition;
import game.racing.car.model.dto.RacingRoundResult;
import spark.Request;

import java.util.*;

import static game.racing.car.utils.RacingGameUtil.convertToCarWebPosition;
import static game.racing.car.utils.RacingGameUtil.separateCarNamesWithBlank;

public class RacingGameWebRequestHandler {

    private static final String CAR_NAMES_REQUEST_PARAMETER = "names";
    private static final String CAR_NAMES_RESPONSE_PARAMETER = "cars";
    private static final String ROUND_COUNT_REQUEST_PARAMETER = "turn";
    private static final String GAME_RESULT_WINNER_PARAMETER = "winners";
    private static final String GAME_RESULT_ROUND_PARAMETER = "round";

    public static Map<String, Object> handleNameRequest(Request request, RacingGameFactory racingGameFactory) {
        String[] carNamesArray = separateCarNamesWithBlank(request.queryParams(CAR_NAMES_REQUEST_PARAMETER));
        racingGameFactory.setCarNames(carNamesArray);

        List<String> carNames = Arrays.asList(carNamesArray);
        Map<String, Object> model = new HashMap<>();
        model.put(CAR_NAMES_RESPONSE_PARAMETER, carNames);
        return model;
    }

    public static Map<String, Object> handleResultRequest(Request request, RacingGameFactory racingGameFactory) {
        Integer roundCount = Integer.valueOf(request.queryParams(ROUND_COUNT_REQUEST_PARAMETER));
        racingGameFactory.setRoundCount(roundCount);

        Map<String, Object> model = new HashMap<>();

        registerRacingGameEvents(model);
        RacingGame racingGame = racingGameFactory.create();
        racingGame.start();

        while (!RacingGameComplete(model)) {
            // 레이싱 게임이 끝날때까지 대기.
        }
        return model;
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
