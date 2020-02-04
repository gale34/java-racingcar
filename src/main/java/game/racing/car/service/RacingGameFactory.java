package game.racing.car.service;

import game.racing.car.model.Cars;

public class RacingGameFactory {
    private String[] carNames;
    private Integer roundCount;

    public void setCarNames(String[] carNames) {
        this.carNames = carNames;
    }

    public void setRoundCount(Integer roundCount) {
        this.roundCount = roundCount;
    }

    public RacingGame create() {
        if (!isReady()) {
            throw new RuntimeException("car or round count invalid.");
        }
        return new RacingGame(new Cars(carNames), roundCount);
    }

    private Boolean isReady() {
        if (carNames.length == 0) {
            return false;
        }

        if (roundCount < 0) {
            return false;
        }

        return true;
    }
}
