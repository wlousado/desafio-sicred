package br.com.sicred.impl.cpf.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public enum CpfImlEnum {
    UNABLE_TO_VOTE(0),
    ABLE_TO_VOTE(1);

    private final Integer vote;
}
