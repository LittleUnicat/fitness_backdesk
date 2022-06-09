package org.unicat.servicefitness.entity;

import java.math.BigDecimal;
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
@ApiModel(value="FitnessProject对象", description="")
public class FitnessProject implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    private String menuFirstId;

    private String menuSecondId;

    private String title;

    private String cover;

    private BigDecimal price;

    private Long buyCount;

    private String status;

    private Integer isDeleted;

    private Date gmtCreate;

    private Date gmtModified;


}
