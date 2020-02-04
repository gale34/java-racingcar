package game.racing.car;

import game.racing.car.service.RacingGame;
import game.racing.car.utils.RacingGameUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class RacingGameInputTests {

    private static final String TEST_CAR_NAME = "TEST";

    @DisplayName("차 이름 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"pobi,crong,honux", "t1,t2,t3", "abc,bcd,cde"})
    void normalCarNamesInputTest(String carNames) {
        assertDoesNotThrow(() -> RacingGameUtil.separateCarNamesWithComma(carNames));
    }

    @DisplayName("차 이름 테스트 - 공백이 구분자")
    @ParameterizedTest
    @ValueSource(strings = {"pobi crong honux", "t1 t2 t3", "abc bcd cde"})
    void normalCarNamesWithBlankInputTest(String carNames) {
        assertDoesNotThrow(() -> RacingGameUtil.separateCarNamesWithBlank(carNames));
    }

    @DisplayName("라운드 갯수가 0보다 작으면 에러가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {-1, -15})
    void abnormalRoundCountInputTest(int roundCount) {
        assertThatThrownBy(() -> {
            new RacingGame(mock(String.class), roundCount);
        }).isInstanceOf(RuntimeException.class);
    }

    @DisplayName("라운드 갯수가 0보다 같거나 크면 정상.")
    @ParameterizedTest
    @ValueSource(ints = {0, 2, 15})
    void normalRoundCountInputTest(int roundCount) {
        assertDoesNotThrow(() -> new RacingGame(TEST_CAR_NAME, roundCount));
    }
}
