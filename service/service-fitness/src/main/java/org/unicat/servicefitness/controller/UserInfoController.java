package org.unicat.servicefitness.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.unicat.commonutils.R;
import org.unicat.servicefitness.entity.UserInfo;
import org.unicat.servicefitness.entity.vo.UserVo;
import org.unicat.servicefitness.service.UserLoginService;

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
@RequestMapping("/serviceFitness")
@Api(tags = "客户管理")
@CrossOrigin
public class UserInfoController {

    @Autowired
    private UserLoginService userLoginService;
    
    @ApiOperation(value = "获取全部用户（客户和管理员）")
    @GetMapping("/user")
    public R getAllUser(){
        List<UserInfo> userInfos = userLoginService.list();
        return R.ok()
                .data("userList", userInfos);
    }


    @ApiOperation(value = "用户注册")
    @PostMapping("/user")
    public R userRigist(@RequestBody UserVo userVo) {
        String uid = userLoginService.addUser(userVo);
        if(!uid.isEmpty()){
            return R.ok()
                    .data("uid", uid);
        }else{
            return R.error()
                    .message("注册失败");
        }
    }
    
    
    @ApiOperation(value = "修改用户信息")
    @PutMapping("/user")
    public R updateUser(@RequestBody UserInfo userInfo){
        if(userLoginService.updateUser(userInfo)) {
            return R.ok();
        }
        return R.error()
                .message("更新用户信息失败");
    }
    
    
    @ApiOperation(value = "注销用户")
    @DeleteMapping("/user/{uid}")
    public R removeUser(@PathVariable String uid){
        if(userLoginService.removeById(uid)){
            return R.ok();
        }else{
            return R.error()
                    .message("删除用户失败");
        }
    }
}

