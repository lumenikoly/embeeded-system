package com.example.analogclock;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TimeParams {

    private static final double UNIT_PERCENT = 2.5;

    double lineSecondPosition;
    double lineHourPosition;

    double lineSecondSize;
    double lineHourSize;

    double lineSecondWidth;
    double lineHourWidth;

    double secondHandSize;
    double minuteHandSize;
    double hourHandSize;

    double secondHandWidth;
    double minuteHandWidth;
    double hourHandWidth;

    double centerCircleRadius;
    double borderCircleRadius;

    public static TimeParams getTimeParams() {
        return TimeParams.builder()
                .lineSecondPosition(UNIT_PERCENT * 85)
                .lineHourPosition(UNIT_PERCENT * 80)
                .lineSecondSize(UNIT_PERCENT * 4)
                .lineHourSize(UNIT_PERCENT * 8)
                .lineSecondWidth(UNIT_PERCENT * 0.4)
                .lineHourWidth(UNIT_PERCENT * 1)
                .secondHandSize(UNIT_PERCENT * 65)
                .minuteHandSize(UNIT_PERCENT * 50)
                .hourHandSize(UNIT_PERCENT * 40)
                .secondHandWidth(UNIT_PERCENT * 0.6)
                .minuteHandWidth(UNIT_PERCENT * 1)
                .hourHandWidth(UNIT_PERCENT * 1.4)
                .centerCircleRadius(UNIT_PERCENT * 3)
                .borderCircleRadius(UNIT_PERCENT * 92)
                .build();
    }

}
