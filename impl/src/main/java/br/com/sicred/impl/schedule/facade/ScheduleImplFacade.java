package br.com.sicred.impl.schedule.facade;

import br.com.sicred.impl.schedule.model.Schedule;
import br.com.sicred.impl.schedule.model.ScheduleResult;
import br.com.sicred.impl.kafka.producer.ScheduleProducer;
import br.com.sicred.impl.schedule.result.ScheduleResultService;
import br.com.sicred.impl.schedule.service.ScheduleService;
import br.com.sicred.impl.schedule.timer.ScheduleTimer;
import br.com.sicred.impl.vote.model.Vote;
import br.com.sicred.impl.vote.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Timer;

@Component
@RequiredArgsConstructor
public class ScheduleImplFacade {

    private final ScheduleService scheduleService;
    private final ScheduleResultService scheduleResultService;
    private final VoteService voteService;
    private final ScheduleProducer scheduleProducer;

    public Mono<Schedule> findSchedule(Schedule schedule){
        return scheduleService.findSchedule(schedule);
    }

    public Mono<Schedule> save(Schedule saveSchedule) {
        return scheduleService.save(saveSchedule);
    }

    public Flux<Vote> findAllByIdSchedule(String id) {
        return voteService.findAllByIdSchedule(id);
    }

    public Mono<ScheduleResult> saveResult(ScheduleResult scheduleResult) {
        return scheduleResultService.save(scheduleResult);
    }

    public Mono<ScheduleResult> findResult(Schedule schedule) {
        return scheduleResultService.findByIdSchedule(schedule.getId());
    }

    public void sendToKafka(ScheduleResult scheduleResult) {
        scheduleProducer.sendAnalysis(scheduleResult);
    }

    public Mono<Schedule> createAnSchedule(Schedule schedule) {
        return scheduleService.save(schedule)
                .doOnNext(this::scheduleTimer);
    }

    private void scheduleTimer(Schedule schedule) {
        new Timer().schedule(ScheduleTimer.builder()
                        .scheduleImplFacade(this)
                        .schedule(schedule)
                .build(), schedule.getDuration() * 60000);
    }
}
