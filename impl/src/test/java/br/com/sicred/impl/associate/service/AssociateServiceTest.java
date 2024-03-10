package br.com.sicred.impl.associate.service;

import br.com.sicred.impl.associate.repository.AssociateRepository;
import br.com.sicred.impl.associate.stub.AssociateRequestStub;
import br.com.sicred.impl.associate.stub.AssociateResponseStub;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AssociateServiceTest {

    @Mock
    private AssociateRepository associateRepository;
    @InjectMocks
    private AssociateService associateService;

    @Test
    void shouldFindAssociateById(){
        final var expected = AssociateResponseStub.createfindAssociateByIdResponse();

        when(associateRepository.findById(expected.getId()))
                .thenReturn(Mono.just(expected));

        StepVerifier.create(associateService.findById(expected.getId()))
                .assertNext(response -> Assertions.assertEquals(response, expected))
                .verifyComplete();
    }

    @Test
    void shouldGetAssociteByCpf(){
        final var associate = AssociateRequestStub.findByCpfStub();
        final var expected = AssociateResponseStub.createfindAssociateByIdResponse();

        when(associateRepository.findByCpf(expected.getCpf()))
                .thenReturn(Mono.just(expected));

        StepVerifier.create(associateService.getAssociateByCpf(associate))
                .assertNext(response -> Assertions.assertEquals(response, expected))
                .verifyComplete();
    }

    @Test
    void shouldSaveanAssociate(){
        final var associate = AssociateRequestStub.findByCpfStub();
        final var expected = AssociateResponseStub.createfindAssociateByIdResponse();

        when(associateRepository.save(associate))
                .thenReturn(Mono.just(expected));

        StepVerifier.create(associateService.save(associate))
                .assertNext(response -> Assertions.assertEquals(response, expected))
                .verifyComplete();
    }
}
