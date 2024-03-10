package br.com.sicred.commons.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum VoteEnum {

    YES(1),
    NO(0);

    private final Integer valueVote;
}
