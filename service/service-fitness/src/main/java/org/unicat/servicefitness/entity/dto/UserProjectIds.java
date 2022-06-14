package org.unicat.servicefitness.entity.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class UserProjectIds {
    private String userId;
    private String projectId;
}
