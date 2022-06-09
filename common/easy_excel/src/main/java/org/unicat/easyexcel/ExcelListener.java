package org.unicat.easyexcel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import org.unicat.commonutils.ColorLogInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExcelListener extends AnalysisEventListener<StudentExcel> {

    List<StudentExcel> studentExcelList = new ArrayList<>();

    // 一行行读excel内容
    @Override
    public void invoke(StudentExcel student, AnalysisContext analysisContext) {
        System.out.println("***" + student);
        studentExcelList.add(student);
    }

    // 读取完成后执行
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        ColorLogInfo.colorLog("总计：\n", ColorLogInfo.PURPLE, 1, studentExcelList.toString());
    }

    // 读取表头信息
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头信息" + headMap);
    }
}
