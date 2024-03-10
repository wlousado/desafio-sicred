package br.com.sicred.impl.schedule.mapper;

import br.com.sicred.impl.schedule.model.Schedule;

public class ScheduleMapper {

    public static Schedule mapById(String id){
        return Schedule.builder()
                .id(id)
                .build();
    }

    public static Schedule mapByName(String name){
        return Schedule.builder()
                .name(name)
                .build();
    }
}
