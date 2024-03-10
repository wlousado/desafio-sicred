package br.com.sicred.impl.schedule.facade;

import br.com.sicred.impl.kafka.producer.ScheduleProducer;
import br.com.sicred.impl.schedule.result.ScheduleResultService;
import br.com.sicred.impl.schedule.result.stub.ScheduleResultRequestStub;
import br.com.sicred.impl.schedule.result.stub.ScheduleResultResponseStub;
import br.com.sicred.impl.schedule.service.ScheduleService;
import br.com.sicred.impl.schedule.stub.ScheduleRequestStub;
import br.com.sicred.impl.schedule.stub.ScheduleResponseStub;
import br.com.sicred.impl.vote.service.VoteService;
import br.com.sicred.impl.vote.stub.VoteResponseStub;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ScheduleImplFacadeTest {
    @Mock
    private ScheduleService scheduleService;
    @Mock
    private ScheduleResultService scheduleResultService;
    @Mock
    private VoteService voteService;
    @Mock
    private ScheduleProducer scheduleProducer;
    @InjectMocks
    private ScheduleImplFacade scheduleImplFacade;

    @Test
    void shouldFindScheduleWhenCallMethod(){
        final var schedule = ScheduleRequestStub.createScheduleWithIdTestId();
        final var expected = ScheduleResponseStub.createScheduleWithIdtestId();

        Mockito.when(scheduleService.findSchedule(schedule))
                .thenReturn(Mono.just(expected));

        StepVerifier.create(scheduleImplFacade.findSchedule(schedule))
                .assertNext(response -> Assertions.assertEquals(response, expected))
                .verifyComplete();
    }

    @Test
    void shouldSaveWhenCallMethod(){
        final var schedule = ScheduleRequestStub.createScheduleWithIdTestId();
        final var expected = ScheduleResponseStub.createScheduleWithIdtestId();

        Mockito.when(scheduleService.save(schedule))
                .thenReturn(Mono.just(expected));

        StepVerifier.create(scheduleImplFacade.save(schedule))
                .assertNext(response -> Assertions.assertEquals(response, expected))
                .verifyComplete();
    }

    @Test
    void shouldfindAllByIdScheduleWhenCallMethod(){
        final var schedule = ScheduleRequestStub.createScheduleWithIdTestId();
        final var expected = VoteResponseStub.createResponseVoteYesWithScheduleIdTestId();

        Mockito.when(voteService.findAllByIdSchedule(schedule.getId()))
                .thenReturn(Flux.just(expected));

        StepVerifier.create(scheduleImplFacade.findAllByIdSchedule(schedule.getId()))
                .assertNext(response -> Assertions.assertEquals(response, expected))
                .verifyComplete();
    }

    @Test
    void shouldSaveScheduleResultWhenCallMethod(){
        final var scheduleResult = ScheduleResultRequestStub.createScheduleResultRequestSaveModel();
        final var expected = ScheduleResultResponseStub.createScheduleResultResponseSaveModel();

        Mockito.when(scheduleResultService.save(scheduleResult))
                .thenReturn(Mono.just(expected));

        StepVerifier.create(scheduleImplFacade.saveResult(scheduleResult))
                .assertNext(response -> Assertions.assertEquals(response, expected))
                .verifyComplete();
    }

    @Test
    void shouldfindResultWhenCallMethod(){
        final var schedule = ScheduleRequestStub.createScheduleWithIdTestId();
        final var expected = ScheduleResultResponseStub.createScheduleResultResponseSaveModel();

        Mockito.when(scheduleResultService.findByIdSchedule(schedule.getId()))
                .thenReturn(Mono.just(expected));

        StepVerifier.create(scheduleImplFacade.findResult(schedule))
                .assertNext(response -> Assertions.assertEquals(response, expected))
                .verifyComplete();
    }

    @Test
    void shouldsendToKafkaWhenCallMethod(){
        final var expected = ScheduleResultResponseStub.createScheduleResultResponseSaveModel();

        doNothing().when(scheduleProducer).sendAnalysis(expected);

        scheduleImplFacade.sendToKafka(expected);

        verify(scheduleProducer, times(1)).sendAnalysis(expected);
    }

    @Test
    void shouldCreateAnScheduleWhenCallMethod(){
        final var schedule = ScheduleRequestStub.createScheduleRequestModel();
        final var expected = ScheduleResponseStub.createScheduleResponseModel();

        when(scheduleService.save(schedule))
                .thenReturn(Mono.just(expected));

        StepVerifier.create(scheduleImplFacade.createAnSchedule(schedule))
                .assertNext(result -> Assertions.assertEquals(result, expected))
                .verifyComplete();
    }



}
