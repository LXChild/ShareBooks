package me.chong.sharebooksserver.utils;

import org.junit.Test;

public class ExcelUtilsTest {


    @Test
    public void getAllByExcel() throws Exception {
//        ExcelUtils.getAllByExcel("/Users/LXChild/Downloads/书籍清单.xlsx");
        ExcelUtils.getExcel("/Users/LXChild/Downloads/书籍清单2.xls");
    }

}