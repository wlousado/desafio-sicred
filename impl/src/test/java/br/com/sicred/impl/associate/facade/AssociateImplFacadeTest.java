package br.com.sicred.impl.associate.facade;

import br.com.sicred.commons.exception.AssociateAlreadyExistsException;
import br.com.sicred.commons.exception.AssociateUnableToVoteException;
import br.com.sicred.impl.associate.service.AssociateService;
import br.com.sicred.impl.associate.stub.AssociateRequestStub;
import br.com.sicred.impl.associate.stub.AssociateResponseStub;
import br.com.sicred.impl.cpf.service.CpfService;
import br.com.sicred.impl.cpf.stub.CpfImplRequestStub;
import br.com.sicred.impl.cpf.stub.CpfImplResponseStub;
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
public class AssociateImplFacadeTest {

    @Mock
    private CpfService cpfService;

    @Mock
    private AssociateService associateService;

    @InjectMocks
    private AssociateImplFacade associateImplFacade;

    @Test
    void shouldGetAssociateByCpf(){
        final var associate = AssociateRequestStub.createAnAssociate();
        final var expected = AssociateResponseStub.createAnAssociate();

        Mockito.when(associateService.getAssociateByCpf(associate))
                .thenReturn(Mono.just(expected));

        StepVerifier.create(associateImplFacade.getAssociateByCpf(associate))
                .assertNext(response -> Assertions.assertEquals(response, expected))
                .verifyComplete();
    }

    @Test
    void shouldCreateAnAssociate(){
        final var associate = AssociateRequestStub.createAnAssociate();
        final var expected = AssociateResponseStub.createAnAssociate();
        final var cpfRequest = CpfImplRequestStub.createToAssociate();
        final var cpfResponse = CpfImplResponseStub.createAbleToVote();

        Mockito.when(cpfService.verifyCpf(cpfRequest))
                .thenReturn(Mono.just(cpfResponse));
        Mockito.when(associateService.getAssociateByCpf(associate))
                .thenReturn(Mono.empty());
        Mockito.when(associateService.save(associate))
                .thenReturn(Mono.just(expected));

        StepVerifier.create(associateImplFacade.createAssociate(associate))
                .assertNext(response -> Assertions.assertEquals(response, expected))
                .verifyComplete();
    }

    @Test
    void shouldThrowExceptionAssociateUnableToVoteException(){
        final var associate = AssociateRequestStub.createAnAssociate();
        final var cpfRequest = CpfImplRequestStub.createToAssociate();
        final var cpfResponse = CpfImplResponseStub.createUnableToVote();

        Mockito.when(cpfService.verifyCpf(cpfRequest))
                .thenReturn(Mono.just(cpfResponse));

        StepVerifier.create(associateImplFacade.createAssociate(associate))
                .expectError(AssociateUnableToVoteException.class)
                .verify();
    }

    @Test
    void shouldThrowExceptionAssociateAlreadyExistsException(){
        final var associate = AssociateRequestStub.createAnAssociate();
        final var associateResponse = AssociateResponseStub.createAnAssociate();
        final var cpfRequest = CpfImplRequestStub.createToAssociate();
        final var cpfResponse = CpfImplResponseStub.createAbleToVote();

        Mockito.when(cpfService.verifyCpf(cpfRequest))
                .thenReturn(Mono.just(cpfResponse));
        Mockito.when(associateService.getAssociateByCpf(associate))
                .thenReturn(Mono.just(associateResponse));

        StepVerifier.create(associateImplFacade.createAssociate(associate))
                .expectError(AssociateAlreadyExistsException.class)
                .verify();
    }
}
