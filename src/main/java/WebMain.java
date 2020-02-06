import game.racing.car.event.Events;
import game.racing.car.view.impl.IndexRoute;
import game.racing.car.view.impl.NameRoute;
import game.racing.car.view.impl.ResultRoute;

import static spark.Spark.*;

public class WebMain {

    public static void main(String[] args) {
        port(8080);

        get("/", new IndexRoute.get());

        post("/name", new NameRoute.post());

        post("/result", new ResultRoute.post());

        Events.reset();
    }
}
