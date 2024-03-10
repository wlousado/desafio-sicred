package br.com.sicred.impl.cpf.mapper;

import br.com.sicred.impl.cpf.model.request.CpfImplRequest;
import br.com.sicred.integration.cpf.model.request.CpfIntegrationRequest;

public class CpfImplRequestMapper {

    public static CpfIntegrationRequest mapFrom(CpfImplRequest request) {
        return CpfIntegrationRequest.builder()
                .cpf(request.getCpf())
                .build();
    }

    public static CpfImplRequest mapFrom(String cpf) {
        return CpfImplRequest.builder()
                .cpf(cpf)
                .build();
    }
}
