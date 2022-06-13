package org.unicat.servicefitness.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import org.unicat.commonutils.ColorLogInfo;
import org.unicat.commonutils.R;
import org.unicat.servicefitness.entity.vo.MenuVo;
import org.unicat.servicefitness.service.MenuFirstService;

import java.io.IOException;
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
@RequestMapping("/fitness/menu")
@CrossOrigin
@Api(tags = "列表获取控制")
public class MenuController {

    @Autowired
    MenuFirstService menuFirstService;

    @PostMapping("/addMenu")
    @ApiOperation("用Excel添加分类")
    public R addMenu(MultipartFile file) {
        try {
            menuFirstService.saveMenu(file);
            return R.ok();
        } catch (IOException ie) {
            return R.error()
                    .data("error", ie);
        }

    }

    @GetMapping("/")
    @ApiOperation("树形结构获取课程列表")
    public R getAllSubject() {
        ColorLogInfo.colorLog("", ColorLogInfo.YELLOW, 1, "树形结构获取课程列表");
        List<MenuVo> allMenu = menuFirstService.getAllMenu();
        return R.ok()
                .data("subjectList", allMenu);
    }

}

