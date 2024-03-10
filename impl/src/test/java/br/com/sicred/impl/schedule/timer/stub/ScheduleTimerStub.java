package br.com.sicred.impl.schedule.timer.stub;

import br.com.sicred.impl.schedule.stub.ScheduleResponseStub;
import br.com.sicred.impl.schedule.timer.ScheduleTimer;

public class ScheduleTimerStub {

    public static ScheduleTimer createScheduleTimer(){
        return ScheduleTimer.builder()
                .schedule(ScheduleResponseStub.createScheduleForTimer())
                .build();
    }
}
