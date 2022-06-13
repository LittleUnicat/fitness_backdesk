package org.unicat.servicefitness.entity.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PriceVo {
    private List<String> prices = new ArrayList<>();
}
