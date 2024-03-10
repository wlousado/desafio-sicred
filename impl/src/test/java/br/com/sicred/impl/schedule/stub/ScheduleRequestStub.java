package br.com.sicred.impl.schedule.stub;

import br.com.sicred.impl.schedule.model.Schedule;

public class ScheduleRequestStub {
    public static Schedule createScheduleWithName() {
        return Schedule.builder()
                .name("schedule")
                .build();
    }

    public static Schedule createScheduleWithId1(){
        return Schedule.builder()
                .id("1")
                .build();
    }

    public static Schedule createScheduleWithIdTestId() {
        return Schedule.builder()
                .id("testId")
                .name("scheduleTest")
                .duration(10)
                .build();
    }

    public static Schedule createScheduleWithIdJustTestId() {
        return Schedule.builder()
                .id("testId")
                .build();
    }

    public static Schedule createScheduleWithDurationNull() {
        return Schedule.builder()
                .id("testId")
                .name("scheduleTest")
                .build();
    }


    public static Schedule createScheduleRequestModel() {
        return Schedule.builder()
                .id("testID")
                .name("scheduleTest")
                .duration(1)
                .build();
    }

    public static Schedule createScheduleRequestModelNoEnd() {
        return Schedule.builder()
                .id("testID")
                .name("scheduleTest")
                .build();
    }
}
