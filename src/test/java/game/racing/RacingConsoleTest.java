package game.racing;

import game.GameConsole;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static constant.PrinterConstant.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("레이싱 콘솔은")
public class RacingConsoleTest {

    private GameConsole console = new RacingConsole();

    @Test
    @DisplayName("자동차 대수를 묻는다.")
    void askCarNumberTest() {

        assertThat(console.askCarNumber())
                .isEqualTo(CAR_NUMBER_PRINT);

    }

    @Test
    @DisplayName("시도할 횟수를 묻는다.")
    void askTryCountTest() {

        assertThat(console.askTryCount())
                .isEqualTo(TRY_COUNT_PRINT);
    }

    @Test
    @DisplayName("실행 결과 구문을 출력한다.")
    void printResultTest() {

        assertThat(console.printResult())
                .isEqualTo(PROCESS_RESULT);
    }
}
