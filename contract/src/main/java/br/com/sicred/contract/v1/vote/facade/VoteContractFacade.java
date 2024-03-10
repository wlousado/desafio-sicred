package br.com.sicred.contract.v1.vote.facade;

import br.com.sicred.contract.v1.vote.mapper.VoteContractRequestMapper;
import br.com.sicred.contract.v1.vote.mapper.VoteContractResponseMapper;
import br.com.sicred.contract.v1.vote.model.request.VoteContractRequest;
import br.com.sicred.contract.v1.vote.model.response.VoteContractResponse;
import br.com.sicred.impl.vote.facade.VoteImplFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class VoteContractFacade {

    private final VoteImplFacade voteService;

    public Mono<VoteContractResponse> doVote(VoteContractRequest contractRequest) {
        final var request = VoteContractRequestMapper.mapFrom(contractRequest);
        return voteService.doVote(request)
                .map(VoteContractResponseMapper::mapFrom);
    }
}
