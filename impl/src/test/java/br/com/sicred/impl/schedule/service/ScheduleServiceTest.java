package br.com.sicred.impl.schedule.service;

import br.com.sicred.commons.exception.ScheduleNotExistsException;
import br.com.sicred.impl.schedule.model.Schedule;
import br.com.sicred.impl.schedule.repository.ScheduleRepository;
import br.com.sicred.impl.schedule.stub.ScheduleRequestStub;
import br.com.sicred.impl.schedule.stub.ScheduleResponseStub;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
public class ScheduleServiceTest {

    @Mock
    private ScheduleRepository scheduleRepository;

    @InjectMocks
    private ScheduleService scheduleService;

    @Test
    void souldThrowExceptionScheduleNotExistsExceptionWhenCallfindScheduleWithName(){
        final var schedule = ScheduleRequestStub.createScheduleWithName();
        Mockito.when(scheduleRepository.findByName(schedule.getName()))
                .thenReturn(Mono.empty());

        StepVerifier.create(scheduleService.findSchedule(schedule))
                .expectError(ScheduleNotExistsException.class)
                .verify();
    }

    @Test
    void souldThrowExceptionScheduleNotExistsExceptionWhenCallfindScheduleWithId(){
        final var schedule = ScheduleRequestStub.createScheduleWithId1();
        Mockito.when(scheduleRepository.findById(schedule.getId()))
                .thenReturn(Mono.empty());

        StepVerifier.create(scheduleService.findSchedule(schedule))
                .expectError(ScheduleNotExistsException.class)
                .verify();
    }

    @Test
    void souldThrowExceptionScheduleNotExistsExceptionWhenScheduleNull(){
        final var schedule = Schedule.builder().build();
        StepVerifier.create(scheduleService.findSchedule(schedule))
                .expectError(ScheduleNotExistsException.class)
                .verify();
    }

    @Test
    void souldCreateAnSchedule(){
        final var schedule = ScheduleRequestStub.createScheduleWithIdTestId();
        final var expected = ScheduleResponseStub.createScheduleWithIdtestId();

        Mockito.when(scheduleRepository.findById(schedule.getId()))
                .thenReturn(Mono.empty());
        Mockito.when(scheduleRepository.save(schedule))
                .thenReturn(Mono.just(expected));

        StepVerifier.create(scheduleService.save(schedule))
                .assertNext(response -> Assertions.assertEquals(response, expected))
                .verifyComplete();
    }

    @Test
    void souldCreateAnScheduleAndAutoSetDurationWhenNull(){
        final var schedule = ScheduleRequestStub.createScheduleWithDurationNull();
        final var expected = ScheduleResponseStub.createScheduleWithDurationNull();

        Mockito.when(scheduleRepository.findById(schedule.getId()))
                .thenReturn(Mono.empty());
        Mockito.when(scheduleRepository.save(schedule))
                .thenReturn(Mono.just(expected));

        StepVerifier.create(scheduleService.save(schedule))
                .assertNext(response -> Assertions.assertEquals(response, expected))
                .verifyComplete();
    }
}
