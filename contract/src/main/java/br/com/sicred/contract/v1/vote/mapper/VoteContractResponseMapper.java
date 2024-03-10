package br.com.sicred.contract.v1.vote.mapper;

import br.com.sicred.contract.v1.vote.model.response.VoteContractResponse;
import br.com.sicred.impl.vote.model.Vote;

public class VoteContractResponseMapper {
    public static VoteContractResponse mapFrom(Vote vote) {
        return VoteContractResponse.builder()
                .id(vote.getId())
                .vote(vote.getVote())
                .idAssociate(vote.getIdAssociate())
                .idSchedule(vote.getIdSchedule())
                .build();
    }
}
