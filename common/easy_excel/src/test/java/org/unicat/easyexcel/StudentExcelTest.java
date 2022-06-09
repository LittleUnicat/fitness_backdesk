package org.unicat.easyexcel;

import com.alibaba.excel.EasyExcel;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class StudentExcelTest {

    @Test
    public void writeExcel() {
        String fileName = "W:\\Computer Science\\SpringBoot2\\Guli\\common\\easy_excel\\src\\main\\resources\\excels\\demoStudentData.xlsx";

        EasyExcel.write(fileName, StudentExcel.class)
                .sheet("模拟写入学生数据")
                .doWrite(getDemoData());
    }

    @Test
    public void readExcel(){
        String fileName = "W:\\Computer Science\\SpringBoot2\\Guli\\common\\easy_excel\\src\\main\\resources\\excels\\demoStudentData.xlsx";

        EasyExcel.read(fileName, StudentExcel.class, new ExcelListener())
                .sheet()
                .doRead();
    }

    public List<StudentExcel> getDemoData() {
        List<StudentExcel> studentExcelList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            StudentExcel demoData = new StudentExcel();
            demoData.setSno(i + 1);
            demoData.setSname("DemoData");
            studentExcelList.add(demoData);
        }
        return studentExcelList;
    }
}
