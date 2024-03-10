package br.com.sicred.impl.cpf.stub;

import br.com.sicred.integration.cpf.model.request.CpfIntegrationRequest;

public class CpfIntegrationRequestStub {

    public static CpfIntegrationRequest createRequest() {
        return CpfIntegrationRequest.builder()
                .cpf("00000000000")
                .build();
    }
}
