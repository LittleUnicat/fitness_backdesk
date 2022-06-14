package org.unicat.servicefitness.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
@Api(tags = "登录登出管理")
@CrossOrigin
public class UserLoginController {

    @Autowired
    private UserInfoService userInfoService;


    @ApiOperation(value = "用户注册")
    @PostMapping("/register")
    public R userRigist(@RequestBody UserVo userVo) {
        String uid = userInfoService.addUser(userVo);
        if (!uid.isEmpty()) {
            return R.ok()
                    .data("uid", uid);
        } else {
            return R.error()
                    .message("注册失败");
        }
    }


    @ApiOperation(value = "用户登录")
    @PostMapping("/login")
    public R userLogin(@RequestBody UserInfo userInfo) {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userInfo.getUsername());
        UserInfo user = userInfoService.getOne(queryWrapper);
        if (user != null) {
            if (userInfo.getPassword().equals(user.getPassword())) {
                return R.ok()
                        .message("登录成功")
                        .data("token", user.getId());
            } else {
                return R.error()
                        .message("密码错误，请重新输入密码");
            }

        }
        return R.error()
                .message("登录失败，用户不存在");
    }


    @GetMapping("/userInfo")
    @ApiOperation(value = "登录用户信息")
    public R info(@RequestParam String token) {
        UserInfo userInfo = userInfoService.getById(token);
        if (userInfo != null) {
            return R.ok()
                    .data("roles", userInfo.getRole())
                    .data("name", userInfo.getUsername())
                    .data("avatar", userInfo.getAvatar());
        }
        return R.error()
                .message("用户不存在");
    }

    @ApiOperation(value = "用户退出登录")
    @PostMapping("/logout")
    public R userLogout() {
        return R.ok()
                .message("登出成功");
    }

}

