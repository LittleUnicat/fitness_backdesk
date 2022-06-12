package org.unicat.servicefitness.controller;


import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.unicat.commonutils.R;
import org.unicat.servicefitness.entity.FitnessVideo;
import org.unicat.servicefitness.service.FitnessVideoService;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author unicat
 * @since 2022-06-11
 */
@RestController
@RequestMapping("/fitness/video")
@CrossOrigin
public class FitnessVideoController {
    @Autowired
    private FitnessVideoService videoService;

    @ApiOperation(value = "添加小节")
    @PostMapping("/")
    public R addVideo(@RequestBody FitnessVideo video){
        videoService.save(video);
        return R.ok();
    }

    @ApiOperation(value = "删除小节")
    @DeleteMapping("/{videoId}")
    public R deleteVideo(@PathVariable String videoId){
        // TODO 待完善，删除小节时需要同时把小节的视频内容删除
        videoService.removeById(videoId);
        return R.ok();
    }

    @ApiOperation(value = "修改小节")
    @PutMapping("/")
    public R updateVideo(@RequestBody FitnessVideo video){
        videoService.updateById(video);
        return R.ok();
    }

    @ApiOperation(value = "查询小节")
    @GetMapping("/{videoId}")
    public R getVideo(@PathVariable String videoId){
        FitnessVideo video = videoService.getById(videoId);
        return R.ok()
                .data("video", video);
    }
}

