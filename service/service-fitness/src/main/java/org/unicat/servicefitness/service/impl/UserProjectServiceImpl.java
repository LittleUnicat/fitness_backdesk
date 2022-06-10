package org.unicat.servicefitness.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import org.unicat.commonutils.ResultCode;
import org.unicat.servicebase.exceptionHandler.MyException;
import org.unicat.servicefitness.entity.UserProject;
import org.unicat.servicefitness.mapper.UserProjectMapper;
import org.unicat.servicefitness.service.UserProjectService;
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
public class UserProjectServiceImpl extends ServiceImpl<UserProjectMapper, UserProject> implements UserProjectService {
}
