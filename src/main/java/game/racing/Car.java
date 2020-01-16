package game.racing;

import java.util.ArrayList;
import java.util.List;

import static constant.GameConstant.*;

final class Car {

    private MovementStrategy movementStrategy = new RandomMovementStrategy();
    private int tryCount = ZERO;
    private List<String> tracked = new ArrayList<>();

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
        return (movementStrategy.getValueByStrategy() == STANDARD_MOVEMENT);
    }
}
