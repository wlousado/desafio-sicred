package br.com.sicred.impl.schedule.timer;

import br.com.sicred.impl.kafka.producer.ScheduleProducer;
import br.com.sicred.impl.schedule.facade.ScheduleImplFacade;
import br.com.sicred.impl.schedule.repository.ScheduleRepository;
import br.com.sicred.impl.schedule.result.ScheduleResultService;
import br.com.sicred.impl.schedule.service.ScheduleService;
import br.com.sicred.impl.schedule.stub.ScheduleRequestStub;
import br.com.sicred.impl.schedule.timer.stub.ScheduleTimerStub;
import br.com.sicred.impl.vote.service.VoteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class ScheduleTimerTest {

    private ScheduleService scheduleService;
    private ScheduleResultService scheduleResultService;
    private VoteService voteService;
    private ScheduleProducer scheduleProducer;
    private ScheduleImplFacade scheduleImplFacade;

    @BeforeEach
    void setup(){
        this.scheduleService = new ScheduleService(Mockito.mock(ScheduleRepository.class));
        this.scheduleResultService = Mockito.mock(ScheduleResultService.class);
        this.voteService = Mockito.mock(VoteService.class);
        this.scheduleProducer = Mockito.mock(ScheduleProducer.class);
        this.scheduleImplFacade = Mockito.spy(new ScheduleImplFacade(scheduleService,
                scheduleResultService,
                voteService,
                scheduleProducer));

    }

    @Test
    void shouldRunTimer(){
        final var schedule = ScheduleRequestStub.createScheduleRequestModel();
        final var timer = ScheduleTimerStub.createScheduleTimer();

        timer.setScheduleImplFacade(scheduleImplFacade);
        timer.run();

        Mockito.verify(scheduleImplFacade, times(1)).findSchedule(schedule);
    }
}
