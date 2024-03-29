package br.com.sicred.contract.v1.associate;

import br.com.sicred.commons.exception.model.ErrorModel;
import br.com.sicred.contract.v1.associate.model.response.AssociateResponse;
import br.com.sicred.contract.v1.associate.facade.AssociateContractFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.ErrorMessage;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@Api(tags = "Associate controller")
@RequestMapping("/v1/associate")
@Slf4j
public class AssociateRestController {

    @Autowired
    private AssociateContractFacade associateContractFacade;

    @ApiOperation("Create an associate")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = AssociateResponse.class),
            @ApiResponse(code = 400, message = "BAD REQUEST", response = ErrorModel.class),
            @ApiResponse(code = 409, message = "CONFLICT", response = ErrorModel.class),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR", response = ErrorMessage.class)
    })
    @PostMapping("/{cpf}")
    public Mono<AssociateResponse> createIdForAssociate(@PathVariable String cpf){
        log.info("[ASSOCIATE CONTROLLER] - Create An Associate | cpf: {}", cpf);
        return associateContractFacade.createAssociate(cpf);
    }

    @ApiOperation("get an associate by cpf")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = AssociateResponse.class),
            @ApiResponse(code = 400, message = "BAD REQUEST", response = ErrorModel.class),
            @ApiResponse(code = 404, message = "NOT FOUND", response = ErrorModel.class),
            @ApiResponse(code = 409, message = "CONFLICT", response = ErrorModel.class),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR", response = ErrorMessage.class)
    })
    @GetMapping("/{cpf}")
    public Mono<AssociateResponse> getAssociateFromCpf(@PathVariable String cpf){
        log.info("[ASSOCIATE CONTROLLER] - Get An Associate | cpf: {}", cpf);
        return associateContractFacade.getAssociateByCpf(cpf);
    }
}
