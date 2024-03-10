package br.com.sicred.impl.vote.stub;

import br.com.sicred.commons.enums.VoteEnum;
import br.com.sicred.impl.vote.model.Vote;

public class VoteResponseStub {

    public static Vote createResponseVoteYesWithId1() {
        return Vote.builder()
                .id("1")
                .idAssociate("testId")
                .idSchedule("testId")
                .vote(VoteEnum.YES)
                .build();
    }

    public static Vote createResponseVoteNoWithId1() {
        return Vote.builder()
                .id("1")
                .idAssociate("testId")
                .idSchedule("testId")
                .vote(VoteEnum.NO)
                .build();
    }

    public static Vote createResponseVoteYesWithScheduleIdTestId() {
        return Vote.builder()
                .id("1")
                .idAssociate("testId")
                .idSchedule("testId")
                .vote(VoteEnum.YES)
                .build();
    }
}
