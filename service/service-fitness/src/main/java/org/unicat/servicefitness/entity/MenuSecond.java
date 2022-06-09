package org.unicat.servicefitness.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
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
@ApiModel(value="MenuSecond对象", description="")
public class MenuSecond implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "二级菜单id")
      @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    private String menuFirstId;

    private String title;

    private Integer sort;

    private Date gmtCreate;

    private Date gmtModified;


}
