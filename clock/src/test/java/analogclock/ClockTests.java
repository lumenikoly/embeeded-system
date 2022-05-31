package analogclock;

import com.example.analogclock.TimeParams;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class ClockTests {

    @Test
    @DisplayName("Testing clock params")
    void test() {
        TimeParams timerParams = TimeParams.getTimeParams();

        Assertions.assertEquals(2.5, timerParams.getLineHourWidth());
        Assertions.assertEquals(10.0, timerParams.getLineSecondSize());
        Assertions.assertEquals(200.0, timerParams.getLineHourPosition());
        Assertions.assertEquals(100.0, timerParams.getHourHandSize());
        Assertions.assertEquals(2.5, timerParams.getMinuteHandWidth());
        Assertions.assertEquals(212.5, timerParams.getLineSecondPosition());
    }
}
