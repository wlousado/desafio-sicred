package br.com.sicred.impl.associate.repository;

import br.com.sicred.impl.associate.model.Associate;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface AssociateRepository extends ReactiveMongoRepository<Associate, String> {
    Mono<Associate> findByCpf(String cpf);
}
