package br.com.sicred.impl.associate.stub;

import br.com.sicred.impl.associate.model.Associate;

public class AssociateRequestStub {

    public static Associate findByCpfStub(){
        return Associate.builder()
                .cpf("00000000000")
                .build();
    }

    public static Associate createAnAssociate() {
        return Associate.builder()
                .cpf("12345678912")
                .build();
    }
}
