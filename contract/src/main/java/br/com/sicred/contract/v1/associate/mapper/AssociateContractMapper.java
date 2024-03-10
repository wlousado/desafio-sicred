package br.com.sicred.contract.v1.associate.mapper;

import br.com.sicred.contract.v1.associate.model.response.AssociateResponse;
import br.com.sicred.impl.associate.model.Associate;

public class AssociateContractMapper {
    public static Associate mapFrom(String cpf) {
        return Associate.builder()
                .cpf(cpf)
                .build();
    }

    public static AssociateResponse mapFrom(Associate associate){
        return AssociateResponse.builder()
                .yourId(associate.getId())
                .build();
    }
}
