package game.racing.car.view;

import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.Map;

public class WebConsoleView {

    private WebConsoleView() {
    }

    private static final HandlebarsTemplateEngine templateEngine = new HandlebarsTemplateEngine();

    public static String render(Map<String, Object> model, String templatePath) {
        return templateEngine.render(new ModelAndView(model, templatePath));
    }
}
