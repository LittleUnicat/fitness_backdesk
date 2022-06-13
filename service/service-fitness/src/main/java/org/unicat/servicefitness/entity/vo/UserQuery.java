package org.unicat.servicefitness.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "Teacher查询对象", description = "用户查询对象封装")
public class UserQuery implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private String username;
    
    private String role;

    //注意，这里使用的是String类型，前端传过来的数据无需进行类型转换
    private String begin;   

    private String end;
}
