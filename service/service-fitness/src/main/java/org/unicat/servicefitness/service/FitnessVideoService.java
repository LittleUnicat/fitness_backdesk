package org.unicat.servicefitness.service;

import org.unicat.servicefitness.entity.FitnessVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author unicat
 * @since 2022-06-11
 */
public interface FitnessVideoService extends IService<FitnessVideo> {
    boolean removeByProjectId(String courseId);
}
