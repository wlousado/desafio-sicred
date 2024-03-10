package br.com.sicred.contract.v1.schedule.mapper;

import br.com.sicred.contract.v1.schedule.model.request.ScheduleContractRequest;
import br.com.sicred.impl.schedule.model.Schedule;

public class ScheduleContractRequestMapper {

    public static Schedule mapFrom(ScheduleContractRequest request){
        return Schedule.builder()
                .id(request.id())
                .name(request.name())
                .duration(request.duration())
                .build();
    }

    public static Schedule mapFrom(String id){
        return Schedule.builder()
                .id(id)
                .build();
    }

    public static ScheduleContractRequest mapById(String id){
        return ScheduleContractRequest.builder()
                .id(id)
                .build();
    }

    public static ScheduleContractRequest mapByName(String name){
        return ScheduleContractRequest.builder()
                .name(name)
                .build();
    }
}
