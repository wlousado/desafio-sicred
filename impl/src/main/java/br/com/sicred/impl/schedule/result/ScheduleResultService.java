package br.com.sicred.impl.schedule.result;

import br.com.sicred.commons.exception.ScheduleResultNotFound;
import br.com.sicred.impl.schedule.model.ScheduleResult;
import br.com.sicred.impl.schedule.repository.ScheduleResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ScheduleResultService {

    private final static String ERROR_NOT_FOUND_RESULT = "not found schedule result";

    private final ScheduleResultRepository scheduleResultRepository;

    public Mono<ScheduleResult> findByIdSchedule(String id) {
        return scheduleResultRepository.findByIdSchedule(id)
                .switchIfEmpty(Mono.error(new ScheduleResultNotFound(ERROR_NOT_FOUND_RESULT)));
    }

    public Mono<ScheduleResult> save(ScheduleResult scheduleResult) {
        return scheduleResultRepository.save(scheduleResult);
    }
}
