package org.unicat.servicefitness.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.alibaba.excel.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sun.xml.bind.v2.TODO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.unicat.commonutils.ResultCode;
import org.unicat.servicebase.exceptionHandler.MyException;
import org.unicat.servicefitness.entity.FitnessDescription;
import org.unicat.servicefitness.entity.FitnessProject;
import org.unicat.servicefitness.entity.MenuFirst;
import org.unicat.servicefitness.entity.MenuSecond;
import org.unicat.servicefitness.entity.vo.ProjectPublishVo;
import org.unicat.servicefitness.entity.vo.ProjectQuery;
import org.unicat.servicefitness.entity.vo.ProjectVo;
import org.unicat.servicefitness.mapper.FitnessProjectMapper;
import org.unicat.servicefitness.service.FitnessDescriptionService;
import org.unicat.servicefitness.service.FitnessProjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.unicat.servicefitness.service.MenuFirstService;
import org.unicat.servicefitness.service.MenuSecondService;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author unicat
 * @since 2022-06-09
 */
@Service
public class FitnessProjectServiceImpl extends ServiceImpl<FitnessProjectMapper, FitnessProject> implements FitnessProjectService {

    @Autowired
    FitnessDescriptionService fitnessDescriptionService;

    @Autowired
    MenuFirstService menuFirstService;

    @Autowired
    MenuSecondService menuSecondService;

    @Override
    public List<ProjectVo> getAllProjectVo() {

        List<FitnessProject> fitnessProjectList = this.list();
        List<FitnessDescription> fitnessDescriptionList = fitnessDescriptionService.list();
        List<ProjectVo> projectVoList = new ArrayList<>();

        // 项目表
        for (FitnessProject fitnessProject : fitnessProjectList) {
            ProjectVo projectVo = new ProjectVo();
            BeanUtils.copyProperties(fitnessProject, projectVo);
            projectVoList.add(projectVo);
        }
        // 简介表
        for (FitnessDescription fitnessDescription : fitnessDescriptionList) {
            projectVoList.stream()
                    .filter(vo -> vo.getId().equals(fitnessDescription.getId()))
                    .findFirst()
                    .ifPresent(projectVo -> projectVo.setDescription(fitnessDescription.getDescription()));
        }

        return projectVoList;
    }

    @Override
    public ProjectVo getProjectVo(String id) {

        ProjectVo projectVo = new ProjectVo();
        FitnessProject fitnessProject = this.list()
                .stream()
                .filter(project -> project.getId().equals(id))
                .findFirst()
                .orElse(null);

        BeanUtil.copyProperties(fitnessProject, projectVo);

        fitnessDescriptionService.list()
                .stream()
                .filter(des -> des.getId().equals(id))
                .findFirst()
                .ifPresent(fitnessDescription -> projectVo.setDescription(fitnessDescription.getDescription()));

        return projectVo;
    }

    @Override
    public Boolean updateProject(FitnessProject fitnessProject) {

        FitnessProject fitnessProject2 = this.getById(fitnessProject.getId());
        // 修改数据覆盖，null不覆盖
        BeanUtil.copyProperties(fitnessProject, fitnessProject2,
                CopyOptions.create().setIgnoreNullValue(true));

        if (!this.updateById(fitnessProject2)) {
            throw new MyException(ResultCode.ERROR, "更新用户失败");
        }
        return true;
    }

    @Override
    public void pageQuery(Page<FitnessProject> pageParam, ProjectQuery projectQuery) {
        QueryWrapper<FitnessProject> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("gmt_create");

        if (projectQuery == null) {
            baseMapper.selectPage(pageParam, queryWrapper);
            return;
        }

        String title = projectQuery.getTitle();
        String price = projectQuery.getPrice();
        String menuFirstId = projectQuery.getMenuFirstId();
        String menuSecondId = projectQuery.getMenuSecondId();

        if (!StringUtils.isEmpty(title)) {
            queryWrapper.like("title", title);
        }

        if (!StringUtils.isEmpty(price)) {
            queryWrapper.eq("price", price);
        }

        if (!StringUtils.isEmpty(menuFirstId)) {
            queryWrapper.eq("menu_first_id", menuFirstId);
        }

        if (!StringUtils.isEmpty(menuSecondId)) {
            queryWrapper.eq("menu_second_id", menuSecondId);
        }

        baseMapper.selectPage(pageParam, queryWrapper);
    }

    @Override
    public String addProject(ProjectVo projectVo) {

        try {
            FitnessProject fitnessProject = new FitnessProject();
            BeanUtil.copyProperties(projectVo, fitnessProject);
            this.save(fitnessProject);

            FitnessDescription fitnessDescription = new FitnessDescription();
            BeanUtil.copyProperties(projectVo, fitnessDescription);
            fitnessDescription.setId(fitnessProject.getId());
            fitnessDescriptionService.save(fitnessDescription);

            return fitnessProject.getId();
        } catch (Exception e) {
            throw new MyException(ResultCode.ERROR, "添加项目失败");
        }

    }

    @Override
    public ProjectPublishVo getPublishProjectInfo(String projectId) {

        ProjectPublishVo projectPublishVo = new ProjectPublishVo();
        ProjectVo projectVo = this.getProjectVo(projectId);
        BeanUtil.copyProperties(projectVo, projectPublishVo);
        projectPublishVo.setMenuFirstTitle(
                menuFirstService.getById(projectVo.getMenuFirstId()).getTitle());
        projectPublishVo.setMenuSecondTitle(
                menuSecondService.getById(projectPublishVo.getMenuSecondId()).getTitle());

        return projectPublishVo;
    }

    @Override
    public Boolean deleteProject(String projectId) {
        try {
            // fitnessDescriptionService.removeById(projectId);
            this.removeById(projectId);
            // TODO 删除项目的时候用户已经选择的也要删除？但记录不应该删除
            return true;
        } catch (Exception e) {
            throw new MyException(ResultCode.ERROR, "服务器内部错误");
        }
    }
}
