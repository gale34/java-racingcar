package game.racing;

import java.util.concurrent.ThreadLocalRandom;

import static constant.GameConstant.EXCLUDE_NUMBER;
import static constant.GameConstant.ZERO;

public class RandomMovementStrategy implements MovementStrategy {

    @Override
    public int getValueByStrategy() {
        return ThreadLocalRandom.current()
                .nextInt(ZERO, EXCLUDE_NUMBER);
    }
}
