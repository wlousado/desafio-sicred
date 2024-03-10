package br.com.sicred.impl.associate.service;

import br.com.sicred.commons.exception.AssociateNotExistsException;
import br.com.sicred.impl.associate.model.Associate;
import br.com.sicred.impl.associate.repository.AssociateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class AssociateService {

    private final AssociateRepository associateRepository;

    public Mono<Associate> findById(String id){
        return associateRepository.findById(id)
                .switchIfEmpty(Mono.error(new AssociateNotExistsException()));
    }

    public Mono<Associate> getAssociateByCpf(Associate associate) {
        return associateRepository.findByCpf(associate.getCpf());
    }

    public Mono<Associate> save(Associate associate){
        return associateRepository.save(associate);
    }
}
