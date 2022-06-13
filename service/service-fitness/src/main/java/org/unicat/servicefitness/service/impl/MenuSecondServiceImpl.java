package org.unicat.servicefitness.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.unicat.servicefitness.entity.MenuSecond;
import org.unicat.servicefitness.mapper.MenuSecondMapper;
import org.unicat.servicefitness.service.MenuSecondService;
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
public class MenuSecondServiceImpl extends ServiceImpl<MenuSecondMapper, MenuSecond> implements MenuSecondService {

    @Override
    public MenuSecond existMenuSecond(String title, String menuFirstId) {

        QueryWrapper<MenuSecond> wrapper = new QueryWrapper<>();
        wrapper.eq("title", title)
                .eq("menu_first_id", menuFirstId);
        return this.getOne(wrapper);
    }
}
