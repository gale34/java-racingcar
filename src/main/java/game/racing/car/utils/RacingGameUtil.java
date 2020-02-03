package game.racing.car.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

public class RacingGameUtil {

    private static final String COMMA_DELIMITER = ",";
    private static final String BLANK_DELIMITER = " ";

    public static String[] separateCarNamesWithComma(String line) {
        return line.split(COMMA_DELIMITER);
    }

    public static String[] separateCarNamesWithBlank(String line) {
        return line.split(BLANK_DELIMITER);
    }

    public static Boolean isNoneEmpty(String[] strings) {
        return Arrays.stream(strings)
                .noneMatch(carName -> StringUtils.isEmpty(carName));
    }
}
