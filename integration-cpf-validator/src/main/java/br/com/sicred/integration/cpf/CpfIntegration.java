package br.com.sicred.integration.cpf;

import br.com.sicred.integration.cpf.model.request.CpfIntegrationRequest;
import br.com.sicred.integration.cpf.model.response.CpfIntegrationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@Slf4j
@RequiredArgsConstructor
public class CpfIntegration {

    private final static String USERS = "/users/";
    private final WebClient webClientIntegration;

    public Mono<CpfIntegrationResponse> doVerification(CpfIntegrationRequest request) {
        log.info("[CPF INTEGRATION] - Do Verification to cpf {}", request.getCpf());
        return webClientIntegration.get()
                .uri(uriBuilder -> uriBuilder.path(USERS.concat(request.getCpf()))
                        .build())
                .retrieve()
                .bodyToMono(CpfIntegrationResponse.class);
    }
}
