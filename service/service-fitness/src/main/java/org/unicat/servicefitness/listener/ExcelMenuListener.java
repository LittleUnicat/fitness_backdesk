package org.unicat.servicefitness.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.unicat.commonutils.ColorLogInfo;
import org.unicat.commonutils.ResultCode;
import org.unicat.servicebase.exceptionHandler.MyException;
import org.unicat.servicefitness.entity.MenuFirst;
import org.unicat.servicefitness.entity.MenuSecond;
import org.unicat.servicefitness.entity.vo.MenuVo;
import org.unicat.servicefitness.excel.ExcelMenuData;
import org.unicat.servicefitness.service.MenuFirstService;
import org.unicat.servicefitness.service.MenuSecondService;

import java.util.ArrayList;
import java.util.List;

public class ExcelMenuListener extends AnalysisEventListener<ExcelMenuData> {

    private MenuFirstService menuFirstService;

    private MenuSecondService menuSecondService;


    public ExcelMenuListener() {
    }

    public ExcelMenuListener(MenuFirstService menuFirstService, MenuSecondService menuSecondService) {
        this.menuFirstService = menuFirstService;
        this.menuSecondService = menuSecondService;
    }

    @Override
    public void invoke(ExcelMenuData excelMenuData, AnalysisContext analysisContext) {


        if (menuFirstService == null) {
            throw new MyException(ResultCode.ERROR, "文件数据为空");
        }

        //添加一级分类
        MenuFirst menuFirst = menuFirstService.existMenuFirst(excelMenuData.getMenuFirstTitle());
        //没有相同的
        if (menuFirst == null) {
            menuFirst = new MenuFirst();
            menuFirst.setTitle(excelMenuData.getMenuFirstTitle());
            // 一级课程新添sort
            // 当前一级条目数+1
            int maxSort = menuFirstService.getOne(new QueryWrapper<MenuFirst>()
                    .orderByDesc("sort")
                    .last("limit 1")).getSort();
            menuFirst.setSort(maxSort + 1);

            menuFirstService.save(menuFirst);


            ColorLogInfo.colorLog("", ColorLogInfo.YELLOW, 1, "添加了新的一级菜单:");
            ColorLogInfo.colorLog("", ColorLogInfo.YELLOW, 1, menuFirst.getTitle());
        }

        //获取一级分类id值
        String pid = menuFirst.getId();

        //添加二级分类
        MenuSecond menuSecond = menuSecondService.existMenuSecond(excelMenuData.getMenuSecondTitle(), pid);
        if (menuSecond == null) {
            menuSecond = new MenuSecond();
            menuSecond.setTitle(excelMenuData.getMenuSecondTitle());
            menuSecond.setMenuFirstId(pid);
            // 二级课程新添sort
            // 当前一级条目数的sort
            menuSecond.setSort(menuFirst.getSort());

            menuSecondService.save(menuSecond);

            ColorLogInfo.colorLog("添加了新的二级菜单", ColorLogInfo.YELLOW, 1, menuSecond.getTitle());
        }

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        ColorLogInfo.colorLog("", ColorLogInfo.YELLOW, 1, "保存完成");
    }


}
