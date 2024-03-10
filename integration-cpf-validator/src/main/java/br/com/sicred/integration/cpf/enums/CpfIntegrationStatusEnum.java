package br.com.sicred.integration.cpf.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CpfIntegrationStatusEnum {

    UNABLE_TO_VOTE(0),
    ABLE_TO_VOTE(1);

    final int value;
}
