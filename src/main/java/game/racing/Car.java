package game.racing;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static constant.GameConstant.*;

final class Car implements MovementStrategy {

    private int tryCount = ZERO;
    List<String> tracked = new ArrayList<>();

    Car(final int tryCount){
        this.tryCount = tryCount;
    }

    void move(){

        String load = EMPTY_LOAD;

        for(int i = 0; i < tryCount; i++){
            if(isMovable()){
                load += DISTANCE;
            }

            tracked.add(load);
        }
    }

    String getDistanceByTryCount(final int tryCount){
        return tracked.get(tryCount);
    }

    private boolean isMovable(){
        return (getValueByStrategy() == STANDARD_MOVEMENT);
    }

    @Override
    public int getValueByStrategy() {
        return ThreadLocalRandom.current()
                .nextInt(ZERO, EXCLUDE_NUMBER);
    }
}
