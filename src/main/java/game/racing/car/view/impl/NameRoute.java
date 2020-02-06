package game.racing.car.view.impl;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static game.racing.car.utils.RacingGameUtil.separateCarNamesWithBlank;
import static game.racing.car.view.WebConsoleView.render;

public class NameRoute {

    private NameRoute() {
    }

    public static class post implements Route {

        private static final String CAR_NAMES_REQUEST_PARAMETER = "names";
        private static final String CAR_NAMES_RESPONSE_PARAMETER = "cars";
        private static final String GAME_START_PAGE = "/game.html";

        @Override
        public Object handle(Request request, Response response) {
            String carNameStr = request.queryParams(CAR_NAMES_REQUEST_PARAMETER);
            String[] carNames = separateCarNamesWithBlank(carNameStr);
            registerCarNamesToSession(request.session(), carNames);

            Map<String, Object> model = new HashMap<>();
            model.put(CAR_NAMES_RESPONSE_PARAMETER, Arrays.asList(carNames));
            return render(model, GAME_START_PAGE);
        }

        private void registerCarNamesToSession(Session session, String[] carNamesArray) {
            session.attribute(CAR_NAMES_REQUEST_PARAMETER, carNamesArray);
        }
    }

}
