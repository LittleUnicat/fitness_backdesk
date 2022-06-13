package org.unicat.servicefitness.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "项目分类")
public class ExcelMenuData {

    @ExcelProperty(index = 0)
    @ApiModelProperty(value = "一级目录", example = "有氧运动")
    private String menuFirstTitle;

    @ApiModelProperty(value = "二级目录", example = "马拉松")
    @ExcelProperty(index = 1)
    private String menuSecondTitle;
}
