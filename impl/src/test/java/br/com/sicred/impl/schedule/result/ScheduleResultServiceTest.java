package br.com.sicred.impl.schedule.result;

import br.com.sicred.impl.schedule.repository.ScheduleResultRepository;
import br.com.sicred.impl.schedule.result.stub.ScheduleResultRequestStub;
import br.com.sicred.impl.schedule.result.stub.ScheduleResultResponseStub;
import br.com.sicred.impl.schedule.stub.ScheduleRequestStub;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ScheduleResultServiceTest {

    @Mock
    private ScheduleResultRepository scheduleResultRepository;

    @InjectMocks
    private ScheduleResultService scheduleResultService;

    @Test
    void shouldfingScheduleResult(){
        final var schedule = ScheduleRequestStub.createScheduleWithIdTestId();
        final var expected = ScheduleResultResponseStub.createScheduleResultResponseSaveModel();

        when(scheduleResultRepository.findByIdSchedule(schedule.getId()))
                .thenReturn(Mono.just(expected));

        StepVerifier.create(scheduleResultService.findByIdSchedule(schedule.getId()))
                .assertNext(result  -> Assertions.assertEquals(result, expected))
                .verifyComplete();
    }

    @Test
    void shoulddSaveScheduleResult(){
        final var schedule = ScheduleResultRequestStub.createScheduleResultRequestSaveModel();
        final var expected = ScheduleResultResponseStub.createScheduleResultResponseSaveModel();

        when(scheduleResultRepository.save(schedule))
                .thenReturn(Mono.just(expected));

        StepVerifier.create(scheduleResultService.save(schedule))
                .assertNext(result  -> Assertions.assertEquals(result, expected))
                .verifyComplete();
    }
}
