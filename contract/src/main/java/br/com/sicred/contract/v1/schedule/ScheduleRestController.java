package br.com.sicred.contract.v1.schedule;

import br.com.sicred.commons.exception.model.ErrorModel;
import br.com.sicred.commons.util.JsonUtil;
import br.com.sicred.contract.v1.schedule.facade.ScheduleContractFacade;
import br.com.sicred.contract.v1.schedule.mapper.ScheduleContractRequestMapper;
import br.com.sicred.contract.v1.schedule.model.request.ScheduleContractRequest;
import br.com.sicred.contract.v1.schedule.model.response.ScheduleContractResponse;
import br.com.sicred.contract.v1.schedule.model.response.ScheduleResultContractResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.support.ErrorMessage;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/v1/schedule")
@Slf4j
@Api(tags = "Schedule controller")
@RequiredArgsConstructor
public class ScheduleRestController {

    private final ScheduleContractFacade scheduleContractFacade;

    @ApiOperation("Create An Schedule")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = ScheduleContractResponse.class),
            @ApiResponse(code = 400, message = "BAD REQUEST", response = ErrorModel.class),
            @ApiResponse(code = 404, message = "NOT FOUND", response = ErrorModel.class),
            @ApiResponse(code = 409, message = "CONFLICT", response = ErrorModel.class),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR", response = ErrorMessage.class)
    })
    @PostMapping("/create")
    public Mono<ScheduleContractResponse> createSchedule(@RequestBody @Valid ScheduleContractRequest schedule){
        log.info("[SCHEDULE CONTROLLER] - Create An Schedule - {}", JsonUtil.convertToJson(schedule));
        return scheduleContractFacade.createSchedule(schedule);
    }

    @ApiOperation("Get An Schedule by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = ScheduleContractResponse.class),
            @ApiResponse(code = 400, message = "BAD REQUEST", response = ErrorModel.class),
            @ApiResponse(code = 404, message = "NOT FOUND", response = ErrorModel.class),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR", response = ErrorMessage.class)
    })
    @GetMapping("/id/{id}")
    public Mono<ScheduleContractResponse> getScheduleId(@PathVariable String id){
        log.info("[SCHEDULE CONTROLLER] - Get An Schedule - {}", id);
        final var request = ScheduleContractRequestMapper.mapById(id);
        return scheduleContractFacade.getSchedule(request);
    }

    @ApiOperation("Get result of schedule")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = ScheduleResultContractResponse.class),
            @ApiResponse(code = 400, message = "BAD REQUEST", response = ErrorModel.class),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR", response = ErrorMessage.class)
    })
    @GetMapping("/id/{id}/result")
    public Mono<ScheduleResultContractResponse> getResultSchedule(@PathVariable String id){
        log.info("[SCHEDULE CONTROLLER] Get a Result from Schedule {}", id);
        return scheduleContractFacade.getResult(id);
    }
}
