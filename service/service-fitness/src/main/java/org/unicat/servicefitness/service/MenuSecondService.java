package org.unicat.servicefitness.service;

import org.unicat.servicefitness.entity.MenuSecond;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author unicat
 * @since 2022-06-09
 */
public interface MenuSecondService extends IService<MenuSecond> {
    MenuSecond existMenuSecond(String title, String menuFirstId);
}
