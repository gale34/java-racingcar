package game.racing.car.model.dto;

public class CarWebPosition {
    private final String carName;
    private final String location;

    private static final String WEB_CAR_LOCATION_MARK = "&nbsp;";

    public CarWebPosition(CarPosition position) {
        this.carName = position.getCarName();
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < position.getLocation(); i++) {
            builder.append(WEB_CAR_LOCATION_MARK);
        }
        this.location = builder.toString();
    }

    public String getCarName() {
        return this.carName;
    }

    public String getLocation() {
        return this.location;
    }
}
