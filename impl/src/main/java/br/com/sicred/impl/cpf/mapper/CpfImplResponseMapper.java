package br.com.sicred.impl.cpf.mapper;

import br.com.sicred.impl.cpf.enums.CpfImlEnum;
import br.com.sicred.impl.cpf.model.response.CpfImplResponse;
import br.com.sicred.integration.cpf.enums.CpfIntegrationStatusEnum;
import br.com.sicred.integration.cpf.model.response.CpfIntegrationResponse;

import java.util.Objects;

public class CpfImplResponseMapper {
    public static CpfImplResponse mapFrom(CpfIntegrationResponse cpfIntegrationResponse) {
        return CpfImplResponse.builder()
                .status(getEnum(cpfIntegrationResponse.getStatus()))
                .build();
    }

    public static CpfImlEnum getEnum(CpfIntegrationStatusEnum statusEnum){
        if (statusEnum == CpfIntegrationStatusEnum.ABLE_TO_VOTE) {
            return CpfImlEnum.ABLE_TO_VOTE;
        }
        return CpfImlEnum.UNABLE_TO_VOTE;
    }
}
