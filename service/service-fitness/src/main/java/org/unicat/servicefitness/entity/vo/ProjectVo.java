package org.unicat.servicefitness.entity.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProjectVo {
    private String id;

    private String menuFirstId;

    private String menuSecondId;

    private String title;

    private String cover;

    private BigDecimal price;

    private Long buyCount;

    private String status;

    private String description;
}
