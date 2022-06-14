package org.unicat.servicefitness.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import org.unicat.commonutils.R;
import org.unicat.servicefitness.entity.FitnessProject;
import org.unicat.servicefitness.entity.MenuFirst;
import org.unicat.servicefitness.entity.UserInfo;
import org.unicat.servicefitness.entity.UserProject;
import org.unicat.servicefitness.entity.dto.UserProjectIds;
import org.unicat.servicefitness.entity.vo.ProjectVo;
import org.unicat.servicefitness.entity.vo.UserQuery;
import org.unicat.servicefitness.service.FitnessProjectService;
import org.unicat.servicefitness.service.UserInfoService;
import org.unicat.servicefitness.service.UserProjectService;

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
@RequestMapping("/fitness/userProject")
@Api(tags = "用户管理")
@CrossOrigin
public class UserProjectController {

    @Autowired
    UserProjectService userProjectService;

    @Autowired
    FitnessProjectService fitnessProjectService;

    @GetMapping("/{uid}")
    public R getUserProject(@PathVariable String uid) {
        QueryWrapper<UserProject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", uid);

        List<UserProject> userProjectList = userProjectService.list(queryWrapper);
        List<ProjectVo> userProjectVoList = new ArrayList<>();

        for (UserProject userProject : userProjectList) {
            ProjectVo projectVo = fitnessProjectService.getProjectVo(userProject.getProjectId());
            userProjectVoList.add(projectVo);
        }

        return R.ok()
                .data("userProjectVoList", userProjectVoList);
    }

    @PostMapping("/")
    public R addUserProject(@RequestBody UserProjectIds ids) {


        UserProject userProject = new UserProject();
        userProject.setUserId(ids.getUserId());
        userProject.setProjectId(ids.getProjectId());
        int maxSort = userProjectService.getOne(new QueryWrapper<UserProject>()
                .orderByDesc("sort")
                .last("limit 1")).getSort();
        userProject.setSort(maxSort + 1);

        if (userProjectService.save(userProject)) {
            return R.ok()
                    .message("添加用户项目成功");
        }
        return R.error()
                .message("添加用户项目失败");
    }

    @DeleteMapping("/")
    public R deleteUserProject(@RequestBody String projectId) {
        QueryWrapper<UserProject> userProjectQueryWrapper = new QueryWrapper<>();
        userProjectQueryWrapper.eq("project_id", projectId);

        if (userProjectService.remove(userProjectQueryWrapper)) {
            return R.ok()
                    .message("删除成功");
        }
        return R.error()
                .message("删除失败");
    }
}

