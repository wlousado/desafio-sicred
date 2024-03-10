package br.com.sicred.impl.schedule.stub;

import br.com.sicred.impl.schedule.model.Schedule;

import java.time.LocalDateTime;

public class ScheduleResponseStub {
    public static Schedule createScheduleWithIdtestId() {
        return Schedule.builder()
                .id("testId")
                .name("scheduleTest")
                .duration(10)
                .endDate(LocalDateTime.now().plusMinutes(10))
                .initDate(LocalDateTime.now())
                .build();
    }

    public static Schedule createScheduleWithEndDate() {
        return Schedule.builder()
                .id("testId")
                .name("scheduleTest")
                .duration(1)
                .endDate(LocalDateTime.now().minusMinutes(10))
                .initDate(LocalDateTime.now().minusMinutes(20))
                .build();
    }

    public static Schedule createScheduleWithDurationNull() {
        return Schedule.builder()
                .id("testId")
                .name("scheduleTest")
                .duration(1)
                .build();
    }

    public static Schedule createScheduleResponseModel() {
        return Schedule.builder()
                .name("newModel")
                .duration(1)
                .build();
    }

    public static Schedule createScheduleForTimer() {
        return Schedule.builder()
                .id("testID")
                .name("scheduleTest")
                .duration(1)
                .build();
    }
}
