package game.racing;

import java.util.ArrayList;
import java.util.List;

final class RacingCars {

    int tryCount = 0;
    List<Car> cars = new ArrayList<>();

    public RacingCars(final int number, final int tryCount){
        this.tryCount = tryCount;

        for(int i = 0; i < number; i++){
            cars.add(new Car(tryCount));
        }
    }

    public void go(){
        cars.forEach(Car::move);

        final int size = cars.size();

        for(int i = 0; i < tryCount; i++) {
            for(int j = 0; j < size; j++) {
                System.out.println(cars.get(j).getDistanceByTryCount(i));
            }
            System.out.println();
        }
    }
}
