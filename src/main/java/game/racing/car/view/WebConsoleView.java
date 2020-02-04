package game.racing.car.view;

import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.Map;

public class WebConsoleView {

    private static final String INDEX_PAGE = "/index.html";
    private static final String GAME_START_PAGE = "/game.html";
    private static final String GAME_RESULT_PAGE = "/result.html";

    private static final HandlebarsTemplateEngine templateEngine = new HandlebarsTemplateEngine();

    public static String renderIndexPage() {
        return render(null, INDEX_PAGE);
    }

    public static String renderGameStartPage(Map<String, Object> model) {
        return render(model, GAME_START_PAGE);
    }

    public static String renderGameResultPage(Map<String, Object> model) {
        return render(model, GAME_RESULT_PAGE);
    }

    public static String render(Map<String, Object> model, String templatePath) {
        return templateEngine.render(new ModelAndView(model, templatePath));
    }
}
