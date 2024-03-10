package br.com.sicred.impl.schedule.timer;

import br.com.sicred.commons.util.DateFormatterUtil;
import br.com.sicred.commons.util.JsonUtil;
import br.com.sicred.commons.enums.ScheduleResultEnum;
import br.com.sicred.commons.enums.VoteEnum;
import br.com.sicred.impl.schedule.facade.ScheduleImplFacade;
import br.com.sicred.impl.schedule.model.Schedule;
import br.com.sicred.impl.schedule.model.ScheduleResult;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.TimerTask;

@EqualsAndHashCode(callSuper = true)
@Slf4j
@Data
@Builder
public class ScheduleTimer extends TimerTask {

    private ScheduleImplFacade scheduleImplFacade;
    private Schedule schedule;

    @Override
    public void run() {
        scheduleImplFacade.findSchedule(schedule)
                .map(data -> {
                    data.setEndDate(LocalDateTime.now());
                    return data;
                })
                .flatMap(scheduleImplFacade::save)
                .flatMap(schedule -> scheduleImplFacade.findAllByIdSchedule(schedule.getId())
                        .collectList()
                        .flatMap(listVotes -> {

                            Long sim = listVotes.stream().filter(vote -> vote.getVote().equals(VoteEnum.YES)).count();
                            Long nao = listVotes.stream().filter(vote -> vote.getVote().equals(VoteEnum.NO)).count();

                            ScheduleResultEnum resultEnum;
                            if (sim > nao) {
                                resultEnum = ScheduleResultEnum.APROVED;
                            } else if (sim.equals(nao)) {
                                resultEnum = ScheduleResultEnum.TIE;
                            } else {
                                resultEnum = ScheduleResultEnum.REPROVED;
                            }
                            return Mono.just(ScheduleResult.builder()
                                    .idSchedule(schedule.getId())
                                    .votesSim(sim.intValue())
                                    .votesNao(nao.intValue())
                                    .result(resultEnum)
                                    .build());
                        })
                        .flatMap(scheduleImplFacade::saveResult))
                .doOnNext(scheduleImplFacade::sendToKafka)
                .subscribe(result -> log.info("[SCHEDULE TIMER] - end schedule {} - at {}", JsonUtil.convertToJson(result), DateFormatterUtil.dateToString(LocalDateTime.now())));
    }
}
