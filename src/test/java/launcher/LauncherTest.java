package launcher;

import game.racing.RacingGame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("런쳐는")
public class LauncherTest {

    private RacingGame racingGame = RacingGame.create();

    @Test
    @DisplayName("레이싱 게임을 시작시킨다.")
    void startTest() {
        racingGame.start();
    }
}
