package br.com.sicred.impl.cpf.service;

import br.com.sicred.impl.cpf.stub.CpfImplRequestStub;
import br.com.sicred.impl.cpf.stub.CpfImplResponseStub;
import br.com.sicred.impl.cpf.stub.CpfIntegrationRequestStub;
import br.com.sicred.impl.cpf.stub.CpfIntegrationResponseStub;
import br.com.sicred.integration.cpf.CpfIntegration;
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
public class CpfServiceTest {

    @Mock
    private CpfIntegration cpfIntegration;
    @InjectMocks
    private CpfService cpfService;

    @Test
    void shouldDoIntegrationWhenVerifyCpf(){
        final var cpfRequest = CpfImplRequestStub.createRequest();
        final var cpfResponse = CpfImplResponseStub.createAbleToVote();
        final var cpfIntegrationRequest = CpfIntegrationRequestStub.createRequest();
        final var cpfIntegartionResponse = CpfIntegrationResponseStub.createResponse();

        when(cpfIntegration.doVerification(cpfIntegrationRequest))
                .thenReturn(Mono.just(cpfIntegartionResponse));

        StepVerifier.create(cpfService.verifyCpf(cpfRequest))
                .assertNext(response -> Assertions.assertEquals(response, cpfResponse))
                .verifyComplete();
    }

}
