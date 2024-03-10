package br.com.sicred.contract.v1.schedule.facade;

import br.com.sicred.contract.v1.schedule.mapper.ScheduleContractRequestMapper;
import br.com.sicred.contract.v1.schedule.mapper.ScheduleContractResponseMapper;
import br.com.sicred.contract.v1.schedule.mapper.ScheduleResultContractResponseMapper;
import br.com.sicred.contract.v1.schedule.model.request.ScheduleContractRequest;
import br.com.sicred.contract.v1.schedule.model.response.ScheduleContractResponse;
import br.com.sicred.contract.v1.schedule.model.response.ScheduleResultContractResponse;
import br.com.sicred.impl.schedule.facade.ScheduleImplFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ScheduleContractFacade {

    private final ScheduleImplFacade scheduleImplFacade;

    public Mono<ScheduleContractResponse> createSchedule(ScheduleContractRequest request){
        final var schedule = ScheduleContractRequestMapper.mapFrom(request);
        return scheduleImplFacade.createAnSchedule(schedule)
                .map(ScheduleContractResponseMapper::mapFrom);
    }

    public Mono<ScheduleContractResponse> getSchedule(ScheduleContractRequest request) {
        final var schedule = ScheduleContractRequestMapper.mapFrom(request);
        return scheduleImplFacade.findSchedule(schedule)
                .map(ScheduleContractResponseMapper::mapFrom);
    }

    public Mono<ScheduleResultContractResponse> getResult(String id) {
        final var schedule = ScheduleContractRequestMapper.mapFrom(id);
        return scheduleImplFacade.findResult(schedule)
                .map(ScheduleResultContractResponseMapper::mapFrom);
    }
}
