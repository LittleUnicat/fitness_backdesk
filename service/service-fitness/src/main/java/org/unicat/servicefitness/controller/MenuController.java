package org.unicat.servicefitness.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.unicat.commonutils.ColorLogInfo;
import org.unicat.commonutils.R;
import org.unicat.servicefitness.entity.vo.MenuVo;
import org.unicat.servicefitness.service.MenuFirstService;

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

    @GetMapping("/")
    @ApiOperation("树形结构获取课程列表")
    public R getAllSubject() {
        ColorLogInfo.colorLog("", ColorLogInfo.YELLOW, 1, "树形结构获取课程列表");
        List<MenuVo> allMenu = menuFirstService.getAllMenu();
        return R.ok()
                .data("subjectList", allMenu);
    }

}

