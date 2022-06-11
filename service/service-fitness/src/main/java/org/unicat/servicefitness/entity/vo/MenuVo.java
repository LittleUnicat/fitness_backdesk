package org.unicat.servicefitness.entity.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.unicat.servicefitness.entity.MenuSecond;

import java.util.ArrayList;
import java.util.List;

@Data
@ApiModel(value = "前端分类")
public class MenuVo {
    private String id;
    private String title;

    // 一个一级分类对应多个二级分类
    private List<MenuSecond> children = new ArrayList<>();
}
