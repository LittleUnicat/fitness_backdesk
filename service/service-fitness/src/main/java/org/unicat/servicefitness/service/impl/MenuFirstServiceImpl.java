package org.unicat.servicefitness.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.unicat.servicefitness.entity.MenuFirst;
import org.unicat.servicefitness.entity.MenuSecond;
import org.unicat.servicefitness.entity.vo.MenuVo;
import org.unicat.servicefitness.excel.ExcelMenuData;
import org.unicat.servicefitness.listener.ExcelMenuListener;
import org.unicat.servicefitness.mapper.MenuFirstMapper;
import org.unicat.servicefitness.service.MenuFirstService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.unicat.servicefitness.service.MenuSecondService;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author unicat
 * @since 2022-06-09
 */
@Service
public class MenuFirstServiceImpl extends ServiceImpl<MenuFirstMapper, MenuFirst> implements MenuFirstService {

    @Autowired
    MenuSecondService menuSecondService;


    @Override
    public void saveMenu(MultipartFile file) throws IOException {
        InputStream in = file.getInputStream();
        EasyExcel.read(in, ExcelMenuData.class, new ExcelMenuListener(this, menuSecondService))
                .sheet()
                .doRead();
    }
    

    @Override
    public MenuFirst existMenuFirst(String title) {
        return this.getOne(new QueryWrapper<MenuFirst>().eq("title", title));
    }


    @Override
    public List<MenuVo> getAllMenu() {

        List<MenuFirst> menuFirstList = this.list();
        List<MenuSecond> menuSecondList = menuSecondService.list();


        List<MenuVo> menuVoList = new ArrayList<>();

        // 一级列表遍历
        for (MenuFirst menuFirst : menuFirstList) {
            // 如果已经加入了则下一条一级
            int j = 0;
            for (; j < menuVoList.size(); j++) {
                if (menuVoList.get(j).getTitle().equals(menuFirst.getTitle())) {
                    break;
                }
            }
            // 已经在列表里面了
            if (j != menuVoList.size()) {
                break;
            }
            // 否则加入
            MenuVo menuVo = new MenuVo();
            BeanUtils.copyProperties(menuFirst, menuVo);
            menuVoList.add(menuVo);
        }

        // 二级列表遍历
        for (MenuSecond menuSecond : menuSecondList) {
            int j = 0;
            for (; j < menuVoList.size(); j++) {
                // 遍历到了twolist的父节点
                if (menuVoList.get(j).getId().equals(menuSecond.getMenuFirstId())) {
                    break;
                }
            }
            // 遍历到了就加入到父列表的孩子当中
            if (j != menuVoList.size()) {
                // 父节点添加孩子
                menuVoList.get(j).getChildren().add(menuSecond);
            }
        }

        return menuVoList;
    }
}
