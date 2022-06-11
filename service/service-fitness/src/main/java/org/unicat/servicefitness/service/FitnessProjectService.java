package org.unicat.servicefitness.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.transaction.annotation.Transactional;
import org.unicat.servicefitness.entity.FitnessProject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.unicat.servicefitness.entity.vo.ProjectQuery;
import org.unicat.servicefitness.entity.vo.ProjectVo;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author unicat
 * @since 2022-06-09
 */
public interface FitnessProjectService extends IService<FitnessProject> {
    
    List<ProjectVo> getAllProjectVo();
    
    ProjectVo getProjectVo(String id);
    
    @Transactional
    Boolean updateProject(FitnessProject fitnessProject);

    void pageQuery(Page<FitnessProject> pageParam, ProjectQuery projectQuery);

    Boolean addProject(ProjectVo projectVo);
}
