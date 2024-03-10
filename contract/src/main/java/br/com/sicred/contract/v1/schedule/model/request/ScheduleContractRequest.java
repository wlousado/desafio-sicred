package br.com.sicred.contract.v1.schedule.model.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record ScheduleContractRequest(
        String id,
        @NotBlank(message = "name cannot be null")
        String name,
        @Min(1)
        @Max(60) Integer duration) {
}

