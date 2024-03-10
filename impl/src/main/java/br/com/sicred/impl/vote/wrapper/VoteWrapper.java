package br.com.sicred.impl.vote.wrapper;

import br.com.sicred.impl.associate.model.Associate;
import br.com.sicred.impl.schedule.model.Schedule;
import br.com.sicred.impl.vote.model.Vote;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VoteWrapper {
    private Schedule schedule;
    private Associate associate;
    private Vote vote;
}
