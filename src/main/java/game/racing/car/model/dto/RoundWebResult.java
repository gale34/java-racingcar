package game.racing.car.model.dto;

import java.util.List;

public class RoundWebResult {
    private Integer roundNumber;
    private List<CarWebPosition> carWebPositions;

    public RoundWebResult(Integer roundNumber, List<CarWebPosition> carWebPositions) {
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
