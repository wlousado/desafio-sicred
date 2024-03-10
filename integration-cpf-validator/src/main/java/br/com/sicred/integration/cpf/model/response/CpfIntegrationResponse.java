package br.com.sicred.integration.cpf.model.response;

import br.com.sicred.integration.cpf.enums.CpfIntegrationStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CpfIntegrationResponse {
    private CpfIntegrationStatusEnum status;
}
