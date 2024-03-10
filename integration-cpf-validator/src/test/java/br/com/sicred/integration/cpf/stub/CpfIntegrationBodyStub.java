package br.com.sicred.integration.cpf.stub;

import br.com.sicred.integration.cpf.enums.CpfIntegrationStatusEnum;
import br.com.sicred.integration.cpf.model.response.CpfIntegrationResponse;

public class CpfIntegrationBodyStub {
    public static CpfIntegrationResponse createReturnAbleToVote() {
        return CpfIntegrationResponse.builder()
                .status(CpfIntegrationStatusEnum.ABLE_TO_VOTE)
                .build();
    }

    public static CpfIntegrationResponse createReturnUAbleToVote() {
        return CpfIntegrationResponse.builder()
                .status(CpfIntegrationStatusEnum.UNABLE_TO_VOTE)
                .build();
    }
}
