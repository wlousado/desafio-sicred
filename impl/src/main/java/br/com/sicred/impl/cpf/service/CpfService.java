package br.com.sicred.impl.cpf.service;

import br.com.sicred.impl.cpf.mapper.CpfImplRequestMapper;
import br.com.sicred.impl.cpf.mapper.CpfImplResponseMapper;
import br.com.sicred.impl.cpf.model.request.CpfImplRequest;
import br.com.sicred.impl.cpf.model.response.CpfImplResponse;
import br.com.sicred.integration.cpf.CpfIntegration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CpfService {

    private final CpfIntegration cpfIntegration;

    public Mono<CpfImplResponse> verifyCpf(CpfImplRequest request){
        var reqIntegration = CpfImplRequestMapper.mapFrom(request);
        return cpfIntegration.doVerification(reqIntegration)
                .map(CpfImplResponseMapper::mapFrom);
    }
}
