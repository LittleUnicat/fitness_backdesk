package org.unicat.servicefitness.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.unicat.commonutils.R;
import org.unicat.servicefitness.entity.FitnessChapter;
import org.unicat.servicefitness.entity.vo.ChapterVo;
import org.unicat.servicefitness.service.FitnessChapterService;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author unicat
 * @since 2022-06-11
 */
@RestController
@RequestMapping("/fitness/chapter")
@Api(tags = "活动小节控制器")
@CrossOrigin
public class FitnessChapterController {
    @Autowired
    private FitnessChapterService chapterService;

    @ApiOperation(value = "获得courseId对应活动和小节")
    @GetMapping("/getChapterVideo/{projectId}")
    public R getChapterVideo(@PathVariable String projectId) {
        List<ChapterVo> chapterVos = chapterService.getChapterVideoByCourseId(projectId);
        return R.ok()
                .data("allChapterVideo", chapterVos);
    }

    @ApiOperation(value = "新增活动")
    @PostMapping("/")
    public R addChapter(
            @ApiParam(name = "chapterVo", value = "活动对象", required = true)
            @RequestBody FitnessChapter chapter) {

        if(chapterService.save(chapter)){
            return R.ok();
        }else{
            return R.error();
        }
        
    }


    @ApiOperation(value = "修改活动")
    @PutMapping("/")
    public R updateChapter(
            @ApiParam(name = "chapter", value = "活动对象", required = true)
            @RequestBody FitnessChapter chapter) {

        chapterService.updateById(chapter);
        return R.ok();
    }

    /**
     * 根据chapter删除活动，如果活动中存在小节则不允许删除
     *
     * @param id 活动ID
     * @return R
     */
    @ApiOperation(value = "根据ID删除活动")
    @DeleteMapping("/{id}")
    public R deleteChapter(
            @ApiParam(name = "id", value = "活动ID", required = true)
            @PathVariable String id) {

        boolean result = chapterService.removeChapterById(id);
        if (result) {
            return R.ok();
        } else {
            return R.error()
                    .message("该活动含有小节，不能删除");
        }
    }

    @ApiOperation(value = "根据ID查询活动")
    @GetMapping("/{id}")
    public R getChapter(
            @ApiParam(name = "id", value = "活动ID", required = true)
            @PathVariable String id) {

        FitnessChapter chapter = chapterService.getById(id);
        return R.ok()
                .data("chapter", chapter);
    }
}

