package br.com.sicred.impl.cpf.model.response;

import br.com.sicred.impl.cpf.enums.CpfImlEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CpfImplResponse {
    private CpfImlEnum status;
}
