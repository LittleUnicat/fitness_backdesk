package org.unicat.servicefitness.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.unicat.commonutils.R;
import org.unicat.servicefitness.entity.FitnessProject;
import org.unicat.servicefitness.service.FitnessProjectService;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author unicat
 * @since 2022-06-09
 */
@RestController
@RequestMapping("/fitness/project")
@CrossOrigin
@Api(tags = "项目管理")
public class FitnessProjectController {

    @Autowired
    FitnessProjectService projectService;


    @ApiOperation(value = "获取全部项目")
    @GetMapping("/")
    public R getAllProject() {
        List<FitnessProject> fitnessProjects = projectService.list();
        if (fitnessProjects != null) {
            return R.ok()
                    .data("projects", fitnessProjects);
        } else {
            return R.error()
                    .message("获取项目失败");
        }
    }


    @ApiOperation(value = "添加项目")
    @PostMapping("/")
    public R addProject(@RequestBody FitnessProject fitnessProject) {
        if (projectService.save(fitnessProject)) {
            return R.ok()
                    .message("添加项目成功");
        } else {
            return R.error()
                    .message("添加项目失败");
        }
    }


    @ApiOperation(value = "修改项目信息")
    @PutMapping("/")
    public R updateProject(@RequestBody FitnessProject fitnessProject) {
        if (projectService.updateProject(fitnessProject)) {
            return R.ok()
                    .message("更新项目成功");
        }
        return R.error()
                .message("更新项目失败");
    }

}

