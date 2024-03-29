package br.com.sicred.impl.vote.model;

import br.com.sicred.commons.enums.VoteEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Document
public class Vote {
    @Id
    private String id;
    private String idSchedule;
    private String idAssociate;
    private VoteEnum vote;
}
