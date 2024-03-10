package br.com.sicred.contract.v1.vote;

import br.com.sicred.commons.exception.model.ErrorModel;
import br.com.sicred.commons.util.JsonUtil;
import br.com.sicred.contract.v1.vote.facade.VoteContractFacade;
import br.com.sicred.contract.v1.vote.model.request.VoteContractRequest;
import br.com.sicred.contract.v1.vote.model.response.VoteContractResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.support.ErrorMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RequestMapping("/v1/vote")
@Api(tags = "Vote controller")
@RestController
@RequiredArgsConstructor
@Slf4j
public class VoteRestController {


    final private VoteContractFacade voteContractFacade;

    @ApiOperation("Vote to in schedule")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = VoteContractResponse.class),
            @ApiResponse(code = 400, message = "BAD REQUEST", response = ErrorModel.class),
            @ApiResponse(code = 404, message = "NOT FOUND", response = ErrorModel.class),
            @ApiResponse(code = 409, message = "CONFLICT", response = ErrorModel.class),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR", response = ErrorMessage.class)
    })
    @PostMapping
    public Mono<VoteContractResponse> doVoteYes(@RequestBody @Valid VoteContractRequest request){
        log.info("[VOTE CONTROLLER] - Do Vote assoc: {}", JsonUtil.convertToJson(request));
        return voteContractFacade.doVote(request);
    }
}
