package br.com.sicred.impl.associate.facade;

import br.com.sicred.commons.exception.AssociateAlreadyExistsException;
import br.com.sicred.commons.exception.AssociateNotExistsException;
import br.com.sicred.commons.exception.AssociateUnableToVoteException;
import br.com.sicred.impl.associate.model.Associate;
import br.com.sicred.impl.associate.service.AssociateService;
import br.com.sicred.impl.cpf.enums.CpfImlEnum;
import br.com.sicred.impl.cpf.mapper.CpfImplRequestMapper;
import br.com.sicred.impl.cpf.service.CpfService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class AssociateImplFacade {
    private final CpfService cpfService;
    private final AssociateService associateService;

    public Mono<Associate> createAssociate(Associate associate) {
        final var cpfRequest = CpfImplRequestMapper.mapFrom(associate.getCpf());

        return cpfService.verifyCpf(cpfRequest)
                .flatMap(cpf -> {
                    if (cpf.getStatus().equals(CpfImlEnum.ABLE_TO_VOTE)) return Mono.just(associate);
                    return Mono.error(new AssociateUnableToVoteException(String.format("cpf %1s is unable to vote", associate.getCpf())));
                })
                .flatMap(associateCpf -> associateService.getAssociateByCpf(associate)
                        .hasElement()
                        .flatMap(element -> {
                            if (element) {
                                return Mono.error(new AssociateAlreadyExistsException(String.format("%1s already exist in database", associateCpf.getCpf())));
                            }
                            return associateService.save(associateCpf);
                        }));
    }

    public Mono<Associate> getAssociateByCpf(Associate associate) {
        return associateService.getAssociateByCpf(associate)
                .switchIfEmpty(Mono.error(new AssociateNotExistsException()));
    }
}
