package br.com.sicred.impl.cpf.stub;

import br.com.sicred.impl.cpf.model.request.CpfImplRequest;

public class CpfImplRequestStub {
    public static CpfImplRequest createRequest() {
        return CpfImplRequest.builder()
                .cpf("00000000000")
                .build();
    }

    public static CpfImplRequest createToAssociate() {
        return CpfImplRequest.builder()
                .cpf("12345678912")
                .build();
    }
}
