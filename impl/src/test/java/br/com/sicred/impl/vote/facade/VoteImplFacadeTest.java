package br.com.sicred.impl.vote.facade;

import br.com.sicred.commons.exception.AssociateAlreadyVoteException;
import br.com.sicred.commons.exception.ScheduleAlreadyEndedException;
import br.com.sicred.impl.associate.service.AssociateService;
import br.com.sicred.impl.associate.stub.AssociateResponseStub;
import br.com.sicred.impl.schedule.service.ScheduleService;
import br.com.sicred.impl.schedule.stub.ScheduleRequestStub;
import br.com.sicred.impl.schedule.stub.ScheduleResponseStub;
import br.com.sicred.impl.vote.service.VoteService;
import br.com.sicred.impl.vote.stub.VoteRequestStub;
import br.com.sicred.impl.vote.stub.VoteResponseStub;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;


@ExtendWith(MockitoExtension.class)
public class VoteImplFacadeTest {

    @Mock
    private AssociateService associateService;
    @Mock
    private ScheduleService scheduleService;
    @Mock
    private VoteService voteService;
    @InjectMocks
    private VoteImplFacade voteImplFacade;

    @Test
    void shouldSaveAndCreateAnVoteWithYesValue(){
        final var vote = VoteRequestStub.createRequestVoteYes();
        final var expected = VoteResponseStub.createResponseVoteYesWithId1();
        final var schedule = ScheduleRequestStub.createScheduleWithIdJustTestId();
        final var scheduleResponse = ScheduleResponseStub.createScheduleWithIdtestId();
        final var associateResponse = AssociateResponseStub.createAssociateWithIdtestId();

        Mockito.when(scheduleService.findSchedule(schedule))
                .thenReturn(Mono.just(scheduleResponse));
        Mockito.when(associateService.findById(vote.getIdAssociate()))
                .thenReturn(Mono.just(associateResponse));
        Mockito.when(voteService.findByIdAssociateAndIdSchedule(vote))
                        .thenReturn(Mono.empty());
        Mockito.when(voteService.save(vote))
                .thenReturn(Mono.just(expected));

        StepVerifier.create(voteImplFacade.doVote(vote))
                .assertNext(result -> Assertions.assertEquals(result, expected))
                .verifyComplete();
    }

    @Test
    void shouldSaveAndCreateAnVoteWithNoValue(){
        final var vote = VoteRequestStub.createRequestVoteNo();
        final var expected = VoteResponseStub.createResponseVoteNoWithId1();
        final var schedule = ScheduleRequestStub.createScheduleWithIdJustTestId();
        final var scheduleResponse = ScheduleResponseStub.createScheduleWithIdtestId();
        final var associateResponse = AssociateResponseStub.createAssociateWithIdtestId();

        Mockito.when(scheduleService.findSchedule(schedule))
                .thenReturn(Mono.just(scheduleResponse));
        Mockito.when(associateService.findById(vote.getIdAssociate()))
                .thenReturn(Mono.just(associateResponse));
        Mockito.when(voteService.findByIdAssociateAndIdSchedule(vote))
                .thenReturn(Mono.empty());
        Mockito.when(voteService.save(vote))
                .thenReturn(Mono.just(expected));

        StepVerifier.create(voteImplFacade.doVote(vote))
                .assertNext(result -> Assertions.assertEquals(result, expected))
                .verifyComplete();
    }

    @Test
    void shouldThrowExceptionScheduleAlreadyEndedExceptionWhenVote(){
        final var vote = VoteRequestStub.createRequestVoteNo();
        final var schedule = ScheduleRequestStub.createScheduleWithIdJustTestId();
        final var scheduleResponse = ScheduleResponseStub.createScheduleWithEndDate();
        final var associateResponse = AssociateResponseStub.createAssociateWithIdtestId();

        Mockito.when(scheduleService.findSchedule(schedule))
                .thenReturn(Mono.just(scheduleResponse));
        Mockito.when(associateService.findById(vote.getIdAssociate()))
                .thenReturn(Mono.just(associateResponse));
        Mockito.when(voteService.findByIdAssociateAndIdSchedule(vote))
                .thenReturn(Mono.empty());

        StepVerifier.create(voteImplFacade.doVote(vote))
                .expectError(ScheduleAlreadyEndedException.class)
                .verify();
    }

    @Test
    void souldThrowAssociateAlreadyVoteExceptionWhenVote(){
        final var vote = VoteRequestStub.createRequestVoteNo();
        final var schedule = ScheduleRequestStub.createScheduleWithIdJustTestId();
        final var scheduleResponse = ScheduleResponseStub.createScheduleWithEndDate();
        final var associateResponse = AssociateResponseStub.createAssociateWithIdtestId();

        Mockito.when(scheduleService.findSchedule(schedule))
                .thenReturn(Mono.just(scheduleResponse));
        Mockito.when(associateService.findById(vote.getIdAssociate()))
                .thenReturn(Mono.just(associateResponse));
        Mockito.when(voteService.findByIdAssociateAndIdSchedule(vote))
                .thenReturn(Mono.just(vote));

        StepVerifier.create(voteImplFacade.doVote(vote))
                .expectError(AssociateAlreadyVoteException.class)
                .verify();
    }
}
