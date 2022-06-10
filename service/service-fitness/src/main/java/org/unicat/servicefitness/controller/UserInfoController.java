package org.unicat.servicefitness.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.unicat.commonutils.R;
import org.unicat.servicefitness.entity.UserInfo;
import org.unicat.servicefitness.entity.vo.UserVo;
import org.unicat.servicefitness.service.UserInfoService;

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
@RequestMapping("/fitness")
@Api(tags = "客户管理")
@CrossOrigin
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @ApiOperation(value = "获取全部用户（客户和管理员）")
    @GetMapping("/allUser")
    public R getAllUser() {
        List<UserInfo> userInfos = userInfoService.list();
        if (userInfos != null) {
            return R.ok()
                    .data("userList", userInfos);
        }
        return R.error()
                .message("查询失败，暂无用户");
    }


    @ApiOperation(value = "获取某用户（客户或管理员）")
    @GetMapping("/getUser")
    public R getUser(@RequestParam String uid) {
        UserInfo userInfo = userInfoService.getById(uid);
        if (userInfo != null) {
            return R.ok()
                    .data("userInfo", userInfo);
        }
        return R.error()
                .message("查询失败，暂无该用户");
    }


    @ApiOperation(value = "修改用户信息")
    @PutMapping("/user")
    public R updateUser(@RequestBody UserInfo userInfo) {
        if (userInfoService.updateUser(userInfo)) {
            return R.ok()
                    .message("信息已修改");
        }
        return R.error()
                .message("更新用户信息失败");
    }


    @ApiOperation(value = "注销用户")
    @DeleteMapping("/user/{uid}")
    public R removeUser(@PathVariable String uid) {
        if (userInfoService.removeById(uid)) {
            return R.ok()
                    .message("用户已注销");
        } else {
            return R.error()
                    .message("删除用户失败");
        }
    }
}

