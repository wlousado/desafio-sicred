package br.com.sicred.impl.vote.facade;

import br.com.sicred.commons.exception.AssociateAlreadyVoteException;
import br.com.sicred.commons.exception.ScheduleAlreadyEndedException;
import br.com.sicred.commons.util.DateFormatterUtil;
import br.com.sicred.impl.associate.model.Associate;
import br.com.sicred.impl.associate.service.AssociateService;
import br.com.sicred.impl.schedule.mapper.ScheduleMapper;
import br.com.sicred.impl.schedule.model.Schedule;
import br.com.sicred.impl.schedule.service.ScheduleService;
import br.com.sicred.impl.vote.mapper.VoteMapper;
import br.com.sicred.impl.vote.mapper.VoteWrapperMapper;
import br.com.sicred.impl.vote.model.Vote;
import br.com.sicred.impl.vote.service.VoteService;
import br.com.sicred.impl.vote.wrapper.VoteWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Objects;


@Component
@Slf4j
@RequiredArgsConstructor
public class VoteImplFacade {

    private final AssociateService associateService;
    private final ScheduleService scheduleService;
    private final VoteService voteService;

    public Mono<Vote> doVote(Vote vote){
        return Mono.zip(findSchedule(vote),
                        findAssociate(vote),
                        validateIfAssociateAlreadyVote(vote))
                .zipWhen(data -> scheduleValidator(data.getT1()))
                .map(data -> VoteWrapperMapper.mapFrom(data.getT2(), data.getT1().getT2(), data.getT1().getT3()))
                .flatMap(this::realizeVote);
    }

    public Mono<Schedule> findSchedule(Vote vote){
        return scheduleService.findSchedule(ScheduleMapper.mapById(vote.getIdSchedule()));
    }

    public Mono<Associate> findAssociate(Vote vote){
        return associateService.findById(vote.getIdAssociate());
    }

    public Mono<Schedule> scheduleValidator(Schedule schedule){
        if(!Objects.isNull(schedule.getEndDate()) && LocalDateTime.now().isAfter(schedule.getEndDate())){
            log.info("[SCHEDULE SERVICE] - schedule is closed at {}", DateFormatterUtil.dateToString(schedule.getEndDate()));
            return Mono.error(new ScheduleAlreadyEndedException(String.format("schedule %1s already closed at %2s", schedule.getName(),
                    DateFormatterUtil.dateToString(schedule.getEndDate()))));
        } else {
            return Mono.just(schedule);
        }
    }

    public Mono<Vote> validateIfAssociateAlreadyVote(Vote vote){
        return voteService.findByIdAssociateAndIdSchedule(vote)
                .hasElement()
                .flatMap(isAssociateAlreadVote -> {
                    if(isAssociateAlreadVote) {
                        log.info("[ASSOCIATE SERVICE] - associate {} already vote to this schedule", vote.getId());
                        return Mono.error(new AssociateAlreadyVoteException("associate already vote to this schedule"));
                    }
                    return Mono.just(vote);
                });
    }

    public Mono<Vote> realizeVote(VoteWrapper voteWrapper){
        final var vote = VoteMapper.mapFrom(voteWrapper);
        return voteService.save(vote);
    }
}
