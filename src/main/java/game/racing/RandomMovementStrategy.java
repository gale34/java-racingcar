package game.racing;

import java.util.concurrent.ThreadLocalRandom;

public class RandomMovementStrategy implements MovementStrategy {

    @Override
    public int getValueByStrategy() {
        return ThreadLocalRandom.current()
                .nextInt(1, 10);
    }
}
