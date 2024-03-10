package br.com.sicred.impl.schedule.service;

import br.com.sicred.commons.exception.ScheduleAlreadyExistsException;
import br.com.sicred.commons.exception.ScheduleNotExistsException;
import br.com.sicred.impl.schedule.model.Schedule;
import br.com.sicred.impl.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final static String ERROR_SCHEDULE_ALREADY_EXISTS = "schedule already exists";
    private final static String ERROR_SCHEDULE_NOT_EXISTS = "schedule not exists";
    private final ScheduleRepository scheduleRepository;

    public Mono<Schedule> findSchedule(Schedule schedule){
        return Mono.just(Optional.ofNullable(schedule).orElse(Schedule.builder().build()))
                .flatMap(scheduleObj -> {
                    if(!Objects.isNull(scheduleObj.getId()))
                        return scheduleRepository.findById(scheduleObj.getId());
                    if(!Objects.isNull(scheduleObj.getName()))
                        return scheduleRepository.findByName(scheduleObj.getName());
                    return Mono.empty();
                })
                .switchIfEmpty(Mono.error(new ScheduleNotExistsException(ERROR_SCHEDULE_NOT_EXISTS)));
    }

    public Mono<Schedule> save(Schedule schedule) {
        return findSchedule(schedule)
                .hasElement()
                .onErrorReturn(false)
                .flatMap(isExistsSchedule -> {
                    if (Objects.isNull(schedule.getDuration()) || schedule.getDuration() <= 0)
                        schedule.setDuration(1);
                    schedule.setInitDate(LocalDateTime.now());
                    return scheduleRepository.save(schedule);
                })
                .switchIfEmpty(Mono.error(new ScheduleAlreadyExistsException(ERROR_SCHEDULE_ALREADY_EXISTS)));
    }


}
