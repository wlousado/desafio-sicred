package br.com.sicred.commons.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ScheduleResultEnum {

    TIE(2),
    APROVED(1),
    REPROVED(0);

    private final Integer value;
}
