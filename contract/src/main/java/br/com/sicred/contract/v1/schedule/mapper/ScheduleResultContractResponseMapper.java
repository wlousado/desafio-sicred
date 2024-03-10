package br.com.sicred.contract.v1.schedule.mapper;

import br.com.sicred.contract.v1.schedule.model.response.ScheduleResultContractResponse;
import br.com.sicred.impl.schedule.model.ScheduleResult;

public class ScheduleResultContractResponseMapper {
    public static ScheduleResultContractResponse mapFrom(ScheduleResult scheduleResult) {
        return ScheduleResultContractResponse.builder()
                .result(scheduleResult.getResult())
                .votesSim(scheduleResult.getVotesSim())
                .votesNao(scheduleResult.getVotesNao())
                .build();
    }
}
