package com.splanet.splanet.plan.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class PlanResponseDto {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
<<<<<<< HEAD

    @JsonIgnore
    private Boolean accessibility;
    @JsonIgnore
    private Boolean isCompleted;
=======
    private Boolean accessibility;
    private Boolean isCompleted;

>>>>>>> weekly/11
    @JsonIgnore
    private LocalDateTime createdAt;
    @JsonIgnore
    private LocalDateTime updatedAt;
}