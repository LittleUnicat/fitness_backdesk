package org.unicat.servicefitness.service;

import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.unicat.servicefitness.entity.MenuFirst;
import com.baomidou.mybatisplus.extension.service.IService;
import org.unicat.servicefitness.entity.vo.MenuVo;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author unicat
 * @since 2022-06-09
 */
public interface MenuFirstService extends IService<MenuFirst> {
    
//    List<OneMenu> getOneMenu();
    
    List<MenuVo> getAllMenu();
    
    void saveMenu(MultipartFile file) throws IOException;

    MenuFirst existMenuFirst(String title);

}
