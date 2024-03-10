package br.com.sicred.impl.schedule.repository;

import br.com.sicred.impl.schedule.model.ScheduleResult;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface ScheduleResultRepository extends ReactiveMongoRepository<ScheduleResult, String> {

    Mono<ScheduleResult> findByIdSchedule(String idSchedule);
}
