package br.com.sicred.impl.kafka.mapper;

import br.com.sicred.commons.enums.ScheduleResultEnum;
import br.com.sicred.impl.schedule.model.ScheduleResult;
import br.com.sicred.model.kafka.model.ResultEnum;
import br.com.sicred.model.kafka.model.ScheduleResultAvro;
import reactor.core.publisher.Mono;

public class ScheduleAvroMapper {

    public static Mono<ScheduleResultAvro> toAvro(Mono<ScheduleResult> result) {
        return result.map(data -> ScheduleResultAvro.newBuilder()
                .setIdSchedule(data.getIdSchedule())
                .setVoteSim(data.getVotesSim())
                .setVoteNao(data.getVotesNao())
                .build());
    }

    public static ScheduleResultAvro toAvro(ScheduleResult data) {
        return ScheduleResultAvro.newBuilder()
                .setIdSchedule(data.getIdSchedule())
                .setResult(getEnum(data.getResult()))
                .setVoteSim(data.getVotesSim())
                .setVoteNao(data.getVotesNao())
                .build();
    }

    private static ResultEnum getEnum(ScheduleResultEnum resultEnum){
        return ResultEnum.valueOf(resultEnum.name());
    }
}
