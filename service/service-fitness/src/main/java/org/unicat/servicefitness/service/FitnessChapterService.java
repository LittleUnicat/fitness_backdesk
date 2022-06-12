package org.unicat.servicefitness.service;

import org.unicat.servicefitness.entity.FitnessChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import org.unicat.servicefitness.entity.vo.ChapterVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author unicat
 * @since 2022-06-11
 */
public interface FitnessChapterService extends IService<FitnessChapter> {
    List<ChapterVo> getChapterVideoByCourseId(String projectId);

    boolean removeChapterById(String courseId);

    boolean removeByCourseId(String courseId);
}
