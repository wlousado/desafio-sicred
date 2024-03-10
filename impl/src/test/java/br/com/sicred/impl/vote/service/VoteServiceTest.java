package br.com.sicred.impl.vote.service;

import br.com.sicred.impl.vote.repository.VoteRepository;
import br.com.sicred.impl.vote.stub.VoteRequestStub;
import br.com.sicred.impl.vote.stub.VoteResponseStub;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
public class VoteServiceTest {

    @Mock
    private VoteRepository voteRepository;

    @InjectMocks
    private VoteService voteService;

    @Test
    void shouldReturnVoteWhenMethodIsInvoked(){
        final var vote = VoteRequestStub.createRequestVoteNo();

        Mockito.when(voteRepository.findByIdAssociateAndIdSchedule(vote.getIdAssociate(), vote.getIdSchedule()))
                .thenReturn(Mono.just(vote));

        StepVerifier.create(voteService.findByIdAssociateAndIdSchedule(vote))
                .assertNext(response -> Assertions.assertEquals(response, vote))
                .verifyComplete();
    }

    @Test
    void souldSaveVoteWhenMethodIsInvoked(){
        final var vote = VoteRequestStub.createRequestVoteNo();

        Mockito.when(voteRepository.save(vote))
                .thenReturn(Mono.just(vote));

        StepVerifier.create(voteService.save(vote))
                .assertNext(response -> Assertions.assertEquals(response, vote))
                .verifyComplete();
    }

    @Test
    void shouldFinAllByIdSchedule(){
        final var vote = VoteResponseStub.createResponseVoteYesWithId1();

        Mockito.when(voteRepository.findAllByIdSchedule(vote.getIdSchedule()))
                .thenReturn(Flux.just(vote));

        StepVerifier.create(voteService.findAllByIdSchedule(vote.getIdSchedule()))
                .assertNext(response -> Assertions.assertEquals(response, vote))
                .verifyComplete();
    }
}
