package org.unicat.servicefitness.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author unicat
 * @since 2022-06-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "FitnessProject对象", description = "健身项目实体类")
public class FitnessProject implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "项目id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty(value = "一级菜单id")
    private String menuFirstId;

    @ApiModelProperty(value = "二级菜单id")
    private String menuSecondId;

    @ApiModelProperty(value = "项目名称")
    private String title;

    @ApiModelProperty(value = "项目封面")
    private String cover;

    @ApiModelProperty(value = "价格")
    private BigDecimal price;

    @ApiModelProperty(value = "报名人数")
    private Long buyCount;

    @ApiModelProperty(value = "项目状态 Draft未发布  Normal已发布")
    private String status;

    @ApiModelProperty(value = "逻辑注销 1（true）已注销， 0（false）未注销")
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Boolean isDeleted;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


}
