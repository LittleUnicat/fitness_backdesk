package org.unicat.servicefitness.service;

import org.springframework.transaction.annotation.Transactional;
import org.unicat.servicefitness.entity.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.unicat.servicefitness.entity.vo.UserVo;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author unicat
 * @since 2022-06-09
 */
public interface UserLoginService extends IService<UserInfo> {
    @Transactional
    String addUser(UserVo userVo);

    @Transactional
    Boolean updateUser(UserInfo userInfo);
}
