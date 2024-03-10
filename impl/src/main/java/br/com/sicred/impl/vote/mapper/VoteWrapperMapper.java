package br.com.sicred.impl.vote.mapper;

import br.com.sicred.impl.associate.model.Associate;
import br.com.sicred.impl.schedule.model.Schedule;
import br.com.sicred.impl.vote.model.Vote;
import br.com.sicred.impl.vote.wrapper.VoteWrapper;

public class VoteWrapperMapper {
    public static VoteWrapper mapFrom(Schedule schedule, Associate associate, Vote vote){
        return VoteWrapper.builder()
                .schedule(schedule)
                .associate(associate)
                .vote(vote)
                .build();
    }
}
