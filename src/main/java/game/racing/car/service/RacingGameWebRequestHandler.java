package game.racing.car.service;

import game.racing.car.event.Events;
import game.racing.car.event.GameOverEvent;
import game.racing.car.event.RoundOverEvent;
import game.racing.car.model.dto.CarPosition;
import game.racing.car.model.dto.RoundWebResult;

import java.util.*;

import static game.racing.car.utils.RacingGameUtil.convertToCarWebPosition;
import static game.racing.car.utils.RacingGameUtil.separateCarNamesWithBlank;

public class RacingGameWebRequestHandler {

    private static final String CAR_NAMES_RESPONSE_PARAMETER = "cars";
    private static final String GAME_RESULT_WINNER_PARAMETER = "winners";
    private static final String GAME_RESULT_ROUND_PARAMETER = "round";

    public static Map<String, Object> handleNameRequest(String carNameStr, RacingGameFactory racingGameFactory) {
        String[] carNamesArray = separateCarNamesWithBlank(carNameStr);
        racingGameFactory.setCarNames(carNamesArray);

        return putCarsToModel(carNamesArray);
    }

    public static Map<String, Object> handleResultRequest(String roundCountStr, RacingGameFactory racingGameFactory) {
        Integer roundCount = Integer.valueOf(roundCountStr);
        racingGameFactory.setRoundCount(roundCount);

        return putResultToModel(racingGameFactory);
    }

    private static Map<String, Object> putCarsToModel(String[] carNamesArray) {
        List<String> carNames = Arrays.asList(carNamesArray);
        Map<String, Object> model = new HashMap<>();
        model.put(CAR_NAMES_RESPONSE_PARAMETER, carNames);
        return model;
    }

    private static Map<String, Object> putResultToModel(RacingGameFactory racingGameFactory) {
        Map<String, Object> model = new HashMap<>();

        executeGame(racingGameFactory, model);
        return model;
    }

    private static void executeGame(RacingGameFactory racingGameFactory, Map<String, Object> model) {
        registerRacingGameEvents(model);
        RacingGame racingGame = racingGameFactory.create();
        racingGame.start();

        while (!racingGameComplete(model)) {
            // 레이싱 게임이 끝날때까지 대기.
        }
        Events.reset();
    }

    private static void registerRacingGameEvents(Map<String, Object> model) {
        Events.handle((RoundOverEvent event) -> saveCurrentPosition(model, event.getCarPositions()));
        Events.handle((GameOverEvent event) -> saveGameResult(model, event.getWinners()));
    }

    private static void saveCurrentPosition(Map<String, Object> model, List<CarPosition> carPositions) {
        List<RoundWebResult> roundCarPositions = new ArrayList<>();
        if (model.containsKey(GAME_RESULT_ROUND_PARAMETER)) {
            roundCarPositions = (List<RoundWebResult>) model.get(GAME_RESULT_ROUND_PARAMETER);
        }

        roundCarPositions.add(new RoundWebResult(roundCarPositions.size() + 1, convertToCarWebPosition(carPositions)));
        model.put(GAME_RESULT_ROUND_PARAMETER, roundCarPositions);
    }

    private static void saveGameResult(Map<String, Object> model, List<String> winners) {
        model.put(GAME_RESULT_WINNER_PARAMETER, winners);
    }

    private static Boolean racingGameComplete(Map<String, Object> model) {
        return model.containsKey(GAME_RESULT_WINNER_PARAMETER);
    }

}
