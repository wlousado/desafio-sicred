package br.com.sicred.impl.vote.service;

import br.com.sicred.impl.vote.model.Vote;
import br.com.sicred.impl.vote.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class VoteService {

    private final VoteRepository voteRepository;

    public Flux<Vote> findAllByIdSchedule(String id){
        return voteRepository.findAllByIdSchedule(id);
    }
    public Mono<Vote> findByIdAssociateAndIdSchedule(Vote vote){
        return voteRepository.findByIdAssociateAndIdSchedule(vote.getIdAssociate(), vote.getIdSchedule());
    }

    public Mono<Vote> save(Vote vote){
        return voteRepository.save(vote);
    }
}
