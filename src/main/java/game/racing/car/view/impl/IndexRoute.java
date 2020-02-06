package game.racing.car.view.impl;

import spark.Request;
import spark.Response;
import spark.Route;

import static game.racing.car.view.WebConsoleView.render;

public class IndexRoute {

    private IndexRoute() {
    }

    public static class get implements Route {
        private static final String INDEX_PAGE = "/index.html";

        @Override
        public Object handle(Request request, Response response) {
            return render(null, INDEX_PAGE);
        }
    }
}
