package br.com.sicred.impl.vote.mapper;

import br.com.sicred.impl.vote.model.Vote;
import br.com.sicred.impl.vote.wrapper.VoteWrapper;

public class VoteMapper {
    public static Vote mapFrom(VoteWrapper voteWrapper) {
        return Vote.builder()
                .id(voteWrapper.getVote().getId())
                .idAssociate(voteWrapper.getAssociate().getId())
                .idSchedule(voteWrapper.getSchedule().getId())
                .vote(voteWrapper.getVote().getVote())
                .build();
    }
}
