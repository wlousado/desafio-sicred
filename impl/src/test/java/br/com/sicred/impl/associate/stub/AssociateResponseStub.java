package br.com.sicred.impl.associate.stub;

import br.com.sicred.impl.associate.model.Associate;

public class AssociateResponseStub {
    public static Associate createAssociateWithIdtestId() {
        return Associate.builder()
                .id("testId")
                .cpf("00000000000")
                .build();
    }

    public static Associate createfindAssociateByIdResponse() {
        return Associate.builder()
                .id("idAssociate")
                .cpf("00000000000")
                .build();
    }

    public static Associate createAnAssociate() {
        return Associate.builder()
                .id("id")
                .cpf("12345678912")
                .build();
    }
}
