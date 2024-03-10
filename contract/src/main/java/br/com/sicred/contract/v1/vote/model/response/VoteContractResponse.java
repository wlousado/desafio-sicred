package br.com.sicred.contract.v1.vote.model.response;

import br.com.sicred.commons.enums.VoteEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class VoteContractResponse {
    private String id;
    private String idAssociate;
    private String idSchedule;
    private VoteEnum vote;
}
