package br.com.sicred.impl.cpf.stub;

import br.com.sicred.impl.cpf.enums.CpfImlEnum;
import br.com.sicred.impl.cpf.model.response.CpfImplResponse;

public class CpfImplResponseStub {
    public static CpfImplResponse createAbleToVote() {
        return CpfImplResponse.builder()
                .status(CpfImlEnum.ABLE_TO_VOTE)
                .build();
    }

    public static CpfImplResponse createUnableToVote() {
        return CpfImplResponse.builder()
                .status(CpfImlEnum.UNABLE_TO_VOTE)
                .build();
    }
}
