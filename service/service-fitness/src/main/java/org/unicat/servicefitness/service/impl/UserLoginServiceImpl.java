package org.unicat.servicefitness.service.impl;

import org.unicat.servicefitness.entity.UserLogin;
import org.unicat.servicefitness.mapper.UserLoginMapper;
import org.unicat.servicefitness.service.UserLoginService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author unicat
 * @since 2022-06-09
 */
@Service
public class UserLoginServiceImpl extends ServiceImpl<UserLoginMapper, UserLogin> implements UserLoginService {

}
