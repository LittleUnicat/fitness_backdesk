package org.unicat.servicefitness.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import org.unicat.commonutils.R;
import org.unicat.servicefitness.entity.UserInfo;
import org.unicat.servicefitness.entity.vo.UserQuery;
import org.unicat.servicefitness.service.UserInfoService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    @GetMapping("/")
    @ApiOperation(value = "查找所有用户")
    public R findAll() {
        List<UserInfo> list = userInfoService.list();
        return R.ok().data("items", list);
    }

    @GetMapping("/getRandom4User/{num}")
    @ApiOperation(value = "随机4个用户")
    public R getRandom4User(@PathVariable String num) {
        List<UserInfo> userInfos = userInfoService.list();
        List<UserInfo> list = new ArrayList<>();

        // 随机过程
        Random random = new Random();
        for (int i = 1; i <= Integer.parseInt(num); i++) {
            int nextInt = random.nextInt(userInfos.size());
            list.add(userInfos.get(nextInt));
            userInfos.remove(userInfos.get(nextInt));
        }

        return R.ok().data("items", list);
    }


    @GetMapping("/userInfo/{id}")
    @ApiOperation(value = "根据ID查找用户")
    public R findUserById(@ApiParam(name = "id", value = "用户ID", required = true)
                          @PathVariable("id") String id) {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);

        if (id != null) {
            UserInfo userInfo = userInfoService.getById(id);
            if (userInfo != null) {
                return R.ok()
                        .data("user", userInfo);
            } else {
                return R.error()
                        .message("用户不存在");
            }
        } else {
            return R.error()
                    .message("ID不能为空");
        }
    }


    @DeleteMapping("userInfo/{id}")
    @ApiOperation(value = "按ID逻辑删除用户")
    public R deleteTeacher(@ApiParam(name = "id", value = "用户ID", required = true)
                           @PathVariable("id") String id) {
        if (userInfoService.removeById(id)) {
            return R.ok();
        } else {
            return R.error();
        }
    }


    @GetMapping("/page/{current}/{limit}")
    @ApiOperation(value = "分页查询用户")
    public R pageListTeacher(@ApiParam(name = "current", value = "当前页", required = true)
                             @PathVariable("current") Long current,
                             @ApiParam(name = "limit", value = "限制", required = false)
                             @PathVariable("limit") Long limit) {

        Page<UserInfo> pageUser = new Page<>(current, limit);
        userInfoService.page(pageUser, null);

        long total = pageUser.getTotal();
        List<UserInfo> records = pageUser.getRecords();

        return R.ok()
                .data("total", total)
                .data("rows", records);
    }


    @PostMapping("/pageCondition/{current}/{limit}")
    @ApiOperation(value = "多条件组合分页查询用户")
    public R pageListTeacherCondition(@ApiParam(name = "current", value = "当前页", required = true)
                                      @PathVariable("current") Long current,
                                      @ApiParam(name = "limit", value = "限制", required = false)
                                      @PathVariable("limit") Long limit,
                                      @ApiParam(name = "userQuery", value = "用户视图vo对象", required = true)
                                      @RequestBody(required = false) UserQuery userQuery) {

        // 创建page对象
        Page<UserInfo> userInfoPage = new Page<>(current, limit);
        // 构建条件
        QueryWrapper<UserInfo> userInfoQueryWrapper = new QueryWrapper<>();

        String username = userQuery.getUsername();
        String role = userQuery.getRole();
        String begin = userQuery.getBegin();
        String end = userQuery.getEnd();

        // 判断是否为空
        if (!StringUtils.isEmpty(username)) {
            userInfoQueryWrapper.like("username", username);
        }
        if (!StringUtils.isEmpty(role)) {
            userInfoQueryWrapper.eq("role", role);
        }
        if (!StringUtils.isEmpty(begin)) {
            userInfoQueryWrapper.ge("gmt_create", begin);
        }
        if (!StringUtils.isEmpty(end)) {
            userInfoQueryWrapper.le("gmt_create", end);
        }

        userInfoService.page(userInfoPage, userInfoQueryWrapper);

        long total = userInfoPage.getTotal();
        List<UserInfo> records = userInfoPage.getRecords();

        return R.ok()
                .data("total", total)
                .data("rows", records);
    }


    @PostMapping("/userInfo")
    @ApiOperation(value = "添加用户信息")
    public R addTeacher(@ApiParam(name = "user", value = "用户信息", required = true)
                        @RequestBody UserInfo userInfo) {

        boolean flag = userInfoService.save(userInfo);
        return flag ? R.ok() : R.error();
    }


    @PutMapping("/userInfo")
    @ApiOperation(value = "修改用户信息")
    public R updateUser(@ApiParam(name = "user", value = "用户信息", required = true)
                        @RequestBody UserInfo userInfo) {

        if (userInfoService.updateById(userInfo)) {
            return R.ok()
                    .message("更新成功");
        } else {
            return R.ok()
                    .message("更新失败");
        }
    }
}

