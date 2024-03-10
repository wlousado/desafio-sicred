package br.com.sicred.impl.schedule.result.stub;

import br.com.sicred.commons.enums.ScheduleResultEnum;
import br.com.sicred.impl.schedule.model.ScheduleResult;

public class ScheduleResultRequestStub {
    public static ScheduleResult createScheduleResultRequestSaveModel() {
        return ScheduleResult.builder()
                .votesSim(1)
                .votesNao(0)
                .idSchedule("idSchedule")
                .result(ScheduleResultEnum.APROVED)
                .build();
    }
}
