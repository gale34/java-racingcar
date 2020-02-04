package game.racing.car.model.dto;

import java.util.List;

public class RacingRoundResult {
    private Integer roundNumber;
    private List<CarWebPosition> carWebPositions;

    public RacingRoundResult(Integer roundNumber, List<CarWebPosition> carWebPositions) {
        this.roundNumber = roundNumber;
        this.carWebPositions = carWebPositions;
    }

    public Integer getRoundNumber() {
        return this.roundNumber;
    }

    public List<CarWebPosition> getCarWebPositions() {
        return this.carWebPositions;
    }
}
