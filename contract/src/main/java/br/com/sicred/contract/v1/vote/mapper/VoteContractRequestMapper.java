package br.com.sicred.contract.v1.vote.mapper;

import br.com.sicred.contract.v1.vote.model.request.VoteContractRequest;
import br.com.sicred.impl.vote.model.Vote;

public class VoteContractRequestMapper {
    public static Vote mapFrom(VoteContractRequest request){
        return Vote.builder()
                .idSchedule(request.idSchedule())
                .idAssociate(request.idAssociate())
                .vote(request.vote())
                .build();
    }
}
