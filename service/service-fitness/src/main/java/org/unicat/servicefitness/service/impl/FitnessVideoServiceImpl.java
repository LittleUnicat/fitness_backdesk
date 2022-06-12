package org.unicat.servicefitness.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.unicat.servicefitness.entity.FitnessVideo;
import org.unicat.servicefitness.mapper.FitnessVideoMapper;
import org.unicat.servicefitness.service.FitnessVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author unicat
 * @since 2022-06-11
 */
@Service
public class FitnessVideoServiceImpl extends ServiceImpl<FitnessVideoMapper, FitnessVideo> implements FitnessVideoService {
    @Override
    public boolean removeByProjectId(String courseId) {
        QueryWrapper<FitnessVideo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("project_id", courseId);
        int count = baseMapper.delete(queryWrapper);
        return count > 0;
    }
}
