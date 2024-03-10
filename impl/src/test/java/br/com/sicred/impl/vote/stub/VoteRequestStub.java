package br.com.sicred.impl.vote.stub;

import br.com.sicred.commons.enums.VoteEnum;
import br.com.sicred.impl.vote.model.Vote;

public class VoteRequestStub {
    public static Vote createRequestVoteYes() {
        return Vote.builder()
                .idAssociate("testId")
                .idSchedule("testId")
                .vote(VoteEnum.YES)
                .build();
    }


    public static Vote createRequestVoteNo() {
        return Vote.builder()
                .idAssociate("testId")
                .idSchedule("testId")
                .vote(VoteEnum.NO)
                .build();
    }
}
