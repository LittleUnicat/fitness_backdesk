package org.unicat.servicefitness.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import org.springframework.beans.BeanUtils;
import org.unicat.commonutils.ResultCode;
import org.unicat.servicebase.exceptionHandler.MyException;
import org.unicat.servicefitness.entity.UserInfo;
import org.unicat.servicefitness.entity.vo.UserVo;
import org.unicat.servicefitness.mapper.UserInfoMapper;
import org.unicat.servicefitness.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author unicat
 * @since 2022-06-09
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

    @Override
    public String addUser(UserVo userVo) {
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(userVo, userInfo);

        if (!this.save(userInfo)) {
            throw new MyException(ResultCode.ERROR, "创建用户失败");
        }

        return userInfo.getId();
    }

    @Override
    public Boolean updateUser(UserInfo userInfo) {

        UserInfo userInfo2 = this.getById(userInfo.getId());
        // 修改数据覆盖，null不覆盖
        BeanUtil.copyProperties(userInfo, userInfo2,
                CopyOptions.create().setIgnoreNullValue(true));
        
        if(!this.updateById(userInfo2)){
            throw new MyException(ResultCode.ERROR, "更新用户失败");
        }
        
        return true;
    }
}
