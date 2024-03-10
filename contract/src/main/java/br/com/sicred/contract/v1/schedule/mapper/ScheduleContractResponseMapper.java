package br.com.sicred.contract.v1.schedule.mapper;

import br.com.sicred.contract.v1.schedule.model.response.ScheduleContractResponse;
import br.com.sicred.impl.schedule.model.Schedule;

public class ScheduleContractResponseMapper {

    public static ScheduleContractResponse mapFrom(Schedule schedule){
        return ScheduleContractResponse.builder()
                .id(schedule.getId())
                .name(schedule.getName())
                .duration(schedule.getDuration())
                .build();
    }
}
