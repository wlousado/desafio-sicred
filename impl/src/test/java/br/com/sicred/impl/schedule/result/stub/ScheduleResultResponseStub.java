package br.com.sicred.impl.schedule.result.stub;

import br.com.sicred.commons.enums.ScheduleResultEnum;
import br.com.sicred.impl.schedule.model.ScheduleResult;

public class ScheduleResultResponseStub {
    public static ScheduleResult createScheduleResultResponseSaveModel() {
        return ScheduleResult.builder()
                .id("idScheduleResult")
                .votesSim(1)
                .votesNao(0)
                .idSchedule("idSchedule")
                .result(ScheduleResultEnum.APROVED)
                .build();
    }
}
