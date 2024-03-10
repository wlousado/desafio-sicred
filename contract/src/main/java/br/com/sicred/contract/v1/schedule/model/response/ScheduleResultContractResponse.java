package br.com.sicred.contract.v1.schedule.model.response;

import br.com.sicred.commons.enums.ScheduleResultEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleResultContractResponse {
    private Integer votesSim;
    private Integer votesNao;
    private ScheduleResultEnum result;
}
