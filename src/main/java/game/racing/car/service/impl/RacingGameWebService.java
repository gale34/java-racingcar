package game.racing.car.service.impl;

import game.racing.car.event.Events;
import game.racing.car.event.GameOverEvent;
import game.racing.car.event.RoundOverEvent;
import game.racing.car.model.dto.CarPosition;
import game.racing.car.model.dto.RacingRoundResult;
import game.racing.car.service.RacingGame;
import game.racing.car.service.RacingGameService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static game.racing.car.utils.RacingGameUtil.convertToCarWebPosition;

public class RacingGameWebService implements RacingGameService {

    private final RacingGame racingGame;
    private Map<String, Object> result;

    private static final String GAME_RESULT_WINNER_PARAMETER = "winners";
    private static final String GAME_RESULT_ROUND_PARAMETER = "round";

    public RacingGameWebService(RacingGame racingGame) {
        this.racingGame = racingGame;
        this.result = new HashMap<>();
    }

    @Override
    public Map<String, Object> run() {
        registerRacingGameEvents();

        racingGame.start();

        while (!RacingGameComplete()) {
            // 레이싱 게임이 끝날때까지 대기.
        }
        return result;
    }

    private void registerRacingGameEvents() {
        Events.handle((RoundOverEvent event) -> saveCurrentPosition(event.getCarPositions()));
        Events.handle((GameOverEvent event) -> saveGameResult(event.getWinners()));
    }

    private void saveCurrentPosition(List<CarPosition> carPositions) {
        List<RacingRoundResult> roundCarPositions = new ArrayList<>();
        if (result.containsKey(GAME_RESULT_ROUND_PARAMETER)) {
            roundCarPositions = (List<RacingRoundResult>) result.get(GAME_RESULT_ROUND_PARAMETER);
        }

        roundCarPositions.add(new RacingRoundResult(roundCarPositions.size() + 1, convertToCarWebPosition(carPositions)));
        result.put(GAME_RESULT_ROUND_PARAMETER, roundCarPositions);
    }

    private void saveGameResult(List<String> winners) {
        result.put(GAME_RESULT_WINNER_PARAMETER, winners);
    }

    private Boolean RacingGameComplete() {
        return result.containsKey(GAME_RESULT_WINNER_PARAMETER);
    }
}
