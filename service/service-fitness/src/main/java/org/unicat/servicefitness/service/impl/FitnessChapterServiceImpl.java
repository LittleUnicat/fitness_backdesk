package org.unicat.servicefitness.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.unicat.commonutils.ResultCode;
import org.unicat.servicebase.exceptionHandler.MyException;
import org.unicat.servicefitness.entity.FitnessChapter;
import org.unicat.servicefitness.entity.FitnessVideo;
import org.unicat.servicefitness.entity.vo.ChapterVo;
import org.unicat.servicefitness.entity.vo.VideoVo;
import org.unicat.servicefitness.mapper.FitnessChapterMapper;
import org.unicat.servicefitness.service.FitnessChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.unicat.servicefitness.service.FitnessVideoService;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author unicat
 * @since 2022-06-11
 */
@Service
public class FitnessChapterServiceImpl extends ServiceImpl<FitnessChapterMapper, FitnessChapter> implements FitnessChapterService {

    @Autowired
    private FitnessChapterService chapterService;

    @Autowired
    private FitnessVideoService videoService;

    @Override
    public List<ChapterVo> getChapterVideoByCourseId(String projectId) {

        List<ChapterVo> resultList = new ArrayList<>();

        // 根据课程id查询课程里面的所有章节并排序
        List<FitnessChapter> chapterList = chapterService.list(new QueryWrapper<FitnessChapter>()
                .eq("project_id", projectId)
                .orderByAsc("sort"));
        //根据课程id查询课程里面的所有小节并排序
        List<FitnessVideo> videoList = videoService.list(new QueryWrapper<FitnessVideo>()
                .eq("project_id", projectId)
                .orderByAsc("sort"));
        // 遍历查询小节list集合，进行封装
        for (FitnessChapter chapter : chapterList) {
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(chapter, chapterVo);

            resultList.add(chapterVo);

            List<VideoVo> childrenList = new ArrayList<>();
            for (FitnessVideo video : videoList) {
                if (video.getChapterId().equals(chapter.getId())) {
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(video, videoVo);
                    childrenList.add(videoVo);
                }
            }
            chapterVo.setChildren(childrenList);
        }

        return resultList;
    }

    @Override
    public boolean removeChapterById(String projectId) {
        int count = videoService.count(new QueryWrapper<FitnessVideo>().eq("project_id", projectId));
        if (count > 0) {
            throw new MyException(ResultCode.ERROR, "该活动含有小节，不能删除");
        }
        return this.removeById(projectId);
    }

    @Override
    public boolean removeByCourseId(String projectId) {
        QueryWrapper<FitnessChapter> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("project_id", projectId);
        int count = baseMapper.delete(queryWrapper);
        return count > 0;
    }
}
