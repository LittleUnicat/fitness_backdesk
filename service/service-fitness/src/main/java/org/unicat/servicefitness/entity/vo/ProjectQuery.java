package org.unicat.servicefitness.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel(value = "Project查询对象", description = "项目查询对象封装")
@Data
public class ProjectQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "项目名称")
    private String title;

    @ApiModelProperty(value = "费用")
    private String price;

    @ApiModelProperty(value = "一级类别id")
    private String menuFirstId;

    @ApiModelProperty(value = "二级类别id")
    private String menuSecondId;

}