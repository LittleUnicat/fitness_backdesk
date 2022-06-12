package org.unicat.servicefitness.controller;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.unicat.commonutils.ColorLogInfo;
import org.unicat.commonutils.R;
import org.unicat.servicebase.exceptionHandler.MyException;
import org.unicat.servicefitness.entity.FitnessDescription;
import org.unicat.servicefitness.entity.FitnessProject;
import org.unicat.servicefitness.entity.vo.MenuVo;
import org.unicat.servicefitness.entity.vo.ProjectPublishVo;
import org.unicat.servicefitness.entity.vo.ProjectQuery;
import org.unicat.servicefitness.entity.vo.ProjectVo;
import org.unicat.servicefitness.service.FitnessDescriptionService;
import org.unicat.servicefitness.service.FitnessProjectService;

import java.util.ArrayList;
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

    @Autowired
    FitnessDescriptionService fitnessDescriptionService;

    @ApiOperation(value = "获取全部项目")
    @GetMapping("/")
    public R getAllProject() {
        List<ProjectVo> projectVos = projectService.getAllProjectVo();

        return R.ok()
                .data("projects", projectVos);

    }

    @ApiOperation(value = "查询某一项目")
    @GetMapping("/{pid}")
    public R getProject(@PathVariable String pid) {

        ProjectVo projectVo = projectService.getProjectVo(pid);

        ColorLogInfo.colorLog("", ColorLogInfo.YELLOW, 1, "查询某一项目");

        return R.ok()
                .data("project", projectVo);
    }


    @ApiOperation(value = "添加项目")
    @PostMapping("/")
    public R addProject(@RequestBody ProjectVo projectVo) {
        if (projectService.addProject(projectVo)) {
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


    @ApiOperation(value = "删除项目")
    @DeleteMapping("/")
    public R deleteProject(@RequestParam String pid) {
        if (projectService.removeById(pid)) {
            return R.ok()
                    .message("删除项目成功");
        }
        return R.error()
                .message("删除项目失败");
    }


    @ApiOperation(value = "分页项目列表")
    @GetMapping("/{page}/{limit}")
    public R pageQuery(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,

            @ApiParam(name = "courseQuery", value = "查询对象", required = false)
                    ProjectQuery projectQuery) {

        Page<FitnessProject> pageParam = new Page<>(page, limit);

        projectService.pageQuery(pageParam, projectQuery);
        List<FitnessProject> records = pageParam.getRecords();

        long total = pageParam.getTotal();

        return R.ok()
                .data("total", total)
                .data("rows", records);
    }


    @ApiOperation(value = "项目发布信息获取")
    @GetMapping("/getPublishProjectInfo/{projectId}")
    public R getPublishCourseInfo(@PathVariable String projectId) {
        ProjectPublishVo projectPublishInfo = projectService.getPublishProjectInfo(projectId);
        return R.ok()
                .data("projectPublishInfo", projectPublishInfo);
    }

//    @ApiOperation(value = "项目最终发布")
//    @PutMapping("/publishCourse/{courseId}")
//    public R publishCourse(@PathVariable String courseId) {
//        EduCourse course = new EduCourse();
//        course.setId(courseId);
//        course.setStatus("Normal");
//        // 只有不为空的才更新，不为空保持原始值
//        courseService.updateById(course);
//        return R.ok();
//    }
}

