package game.racing.car.view;

        import game.racing.car.utils.RacingGameUtil;
        import spark.ModelAndView;
        import spark.Request;
        import spark.template.handlebars.HandlebarsTemplateEngine;

        import java.util.Arrays;
        import java.util.HashMap;
        import java.util.List;
        import java.util.Map;

public class WebConsoleView {

    private static final String INDEX_PAGE = "/index.html";
    private static final String GAME_START_PAGE = "/game.html";
    private static final String GAME_RESULT_PAGE = "/result.html";

    private static final String CAR_NAMES_PARAMETER = "names";

    public static String renderIndexPage() {
        return render(null, INDEX_PAGE);
    }

    public static String renderGameStartPage(Request request) {
        Map<String, Object> model = new HashMap<>();

        String carNameStr = request.queryParams(CAR_NAMES_PARAMETER);
        List<String> carNames = Arrays.asList(RacingGameUtil.separateCarNamesWithBlank(carNameStr));
        model.put("cars", carNames);

        return render(model, GAME_START_PAGE);
    }

    public static String renderGameResultPage(Request request) {
        return null;
    }

    public static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine()
                .render(new ModelAndView(model, templatePath));
    }
}
