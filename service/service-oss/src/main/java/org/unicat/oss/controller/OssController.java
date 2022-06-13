package org.unicat.oss.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.unicat.commonutils.R;
import org.unicat.oss.service.OssService;


@RestController
@RequestMapping("/fitnessOss")
@Api(tags = "阿里云oss文件上传")
@CrossOrigin
public class OssController {

    @Autowired
    private OssService ossService;


    @ApiOperation("头像上传")
    @PostMapping("/headimg")
    public R uploadHeadImg(MultipartFile file) {
        // 获取上传文件，MultipartFile是spring管理文件的一种对象

        R uploadResult = ossService.uploadHeadImg(file);

        // 若返回正确代码
        if(R.ok().getCode().equals(uploadResult.getCode())){
            return R.ok()
                    .message("头像上传成功")
                    .data("url", uploadResult.getData().get("url"));
        }else{
            return R.error();
        }

    }


    @ApiOperation(value = "文件上传")
    @PostMapping("/project/uploadCover")
    public R upload(
            @ApiParam(name = "file", value = "文件", required = true)
            @RequestParam("file") MultipartFile file){
        R uploadResult = ossService.uploadProjectCover(file);
        //返回r对象
        // 若返回正确代码
        if(R.ok().getCode().equals(uploadResult.getCode())){
            return R.ok()
                    .message("封面上传成功")
                    .data("url", uploadResult.getData().get("url"));
        }else{
            return R.error();
        }

    }


    @ApiOperation("下载课程导入模板")
    @GetMapping("/menuTemplate")
    public R getSubjectTemplate(){
        return R.ok()
                .data("url", "https://unicat.oss-cn-beijing.aliyuncs.com/subjectTemplate/testSubject.xlsx");
    }


}
