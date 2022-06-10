package org.unicat.servicefitness.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import org.unicat.commonutils.ResultCode;
import org.unicat.servicebase.exceptionHandler.MyException;
import org.unicat.servicefitness.entity.FitnessProject;
import org.unicat.servicefitness.entity.UserProject;
import org.unicat.servicefitness.mapper.FitnessProjectMapper;
import org.unicat.servicefitness.service.FitnessProjectService;
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
public class FitnessProjectServiceImpl extends ServiceImpl<FitnessProjectMapper, FitnessProject> implements FitnessProjectService {

    @Override
    public Boolean updateProject(FitnessProject fitnessProject) {

        FitnessProject fitnessProject2 = this.getById(fitnessProject.getId());
        // 修改数据覆盖，null不覆盖
        BeanUtil.copyProperties(fitnessProject, fitnessProject2,
                CopyOptions.create().setIgnoreNullValue(true));

        if (!this.updateById(fitnessProject2)) {
            throw new MyException(ResultCode.ERROR, "更新用户失败");
        }

        return true;

    }
}
