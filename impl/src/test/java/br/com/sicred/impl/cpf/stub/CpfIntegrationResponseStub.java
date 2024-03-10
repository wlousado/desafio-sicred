package br.com.sicred.impl.cpf.stub;

import br.com.sicred.integration.cpf.enums.CpfIntegrationStatusEnum;
import br.com.sicred.integration.cpf.model.response.CpfIntegrationResponse;

public class CpfIntegrationResponseStub {
    public static CpfIntegrationResponse createResponse(){
        return CpfIntegrationResponse.builder()
                .status(CpfIntegrationStatusEnum.ABLE_TO_VOTE)
                .build();
    }
}
