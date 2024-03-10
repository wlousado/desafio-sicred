package br.com.sicred.contract.v1.associate.facade;

import br.com.sicred.contract.v1.associate.mapper.AssociateContractMapper;
import br.com.sicred.contract.v1.associate.model.response.AssociateResponse;
import br.com.sicred.impl.associate.facade.AssociateImplFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class AssociateContractFacade {

    private final AssociateImplFacade associateImplFacade;

    public Mono<AssociateResponse> createAssociate(String cpf) {
        final var associate = AssociateContractMapper.mapFrom(cpf);
        return associateImplFacade.createAssociate(associate)
                .map(AssociateContractMapper::mapFrom);
    }

    public Mono<AssociateResponse> getAssociateByCpf(String cpf) {
        final var associate = AssociateContractMapper.mapFrom(cpf);
        return associateImplFacade.getAssociateByCpf(associate)
                .map(AssociateContractMapper::mapFrom);
    }
}
