package org.unicat.servicefitness.entity.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@ApiModel(value = "课程发布信息")
@Data
public class ProjectPublishVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String title;
    private String cover;
    private String description;
    private String menuFirstId;
    private String menuSecondId;
    private String menuFirstTitle;
    private String menuSecondTitle;
    private String price;
}
