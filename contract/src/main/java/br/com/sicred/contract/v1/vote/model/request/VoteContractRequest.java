package br.com.sicred.contract.v1.vote.model.request;

import br.com.sicred.commons.enums.VoteEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record VoteContractRequest(
        String idAssociate,
        String idSchedule,
        @NotNull(message = "vote cannot be null")
        VoteEnum vote
) {
}
