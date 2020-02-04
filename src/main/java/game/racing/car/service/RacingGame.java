package game.racing.car.service;

import game.racing.car.event.Events;
import game.racing.car.event.GameOverEvent;
import game.racing.car.event.RoundOverEvent;
import game.racing.car.model.Cars;
import game.racing.car.model.dto.CarPosition;

import java.util.List;
import java.util.stream.Collectors;

import static game.racing.car.utils.RacingGameUtil.isNoneEmpty;
import static game.racing.car.utils.RacingGameUtil.separateCarNamesWithComma;

public class RacingGame {
    private Cars cars;
    private final Integer roundCount;

    public RacingGame(String carNameString, Integer roundCount) {
        String[] carNames = separateCarNamesWithComma(carNameString);
        validate(carNames, roundCount);

        this.cars = new Cars(carNames);
        this.roundCount = roundCount;
    }

    public RacingGame(Cars cars, Integer roundCount) {
        this.cars = cars;
        this.roundCount = roundCount;
    }

    public void start() {
        for (int i = 0; i < roundCount; i++) {
            runRound();
        }

        Events.raise(new GameOverEvent(getWinnerNames()));
    }

    private void runRound() {
        cars.moveAll();
        Events.raise(new RoundOverEvent(cars.getCarPositionAll()));
    }

    private void validate(String[] carNames, Integer roundCount) {
        if(!isNoneEmpty(carNames)) {
            throw new RuntimeException("car name length mush be bigger than zero.");
        }

        if (roundCount < 0) {
            throw new RuntimeException("round count must be bigger than zero.");
        }
    }

    private List<String> getWinnerNames() {
        List<CarPosition> carPositions = cars.getCarPositionAll();

        Integer winnerScore = carPositions.stream()
                .mapToInt(carPosition -> carPosition.getLocation())
                .max()
                .orElseThrow(() -> new RuntimeException("Unexpected Error."));

        return carPositions.stream()
                .filter(carPosition -> winnerScore.equals(carPosition.getLocation()))
                .map(carPosition -> carPosition.getCarName())
                .collect(Collectors.toList());
    }
}
