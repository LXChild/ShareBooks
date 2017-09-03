package me.chong.sharebooksserver.utils;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import me.chong.sharebooksserver.entity.Book;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExcelUtils {

    /**
     * 查询指定目录中电子表格中所有的数据
     *
     * @param file 文件完整路径
     * @return
     */
    public static List<Book> getAllByExcel(String file) {
        List<Book> list = new ArrayList<>();
        try {
            Workbook rwb = Workbook.getWorkbook(new File(file));
            Sheet rs = rwb.getSheet(1);//或者rwb.getSheet(0)
            int clos = rs.getColumns();//得到所有的列
            int rows = rs.getRows();//得到所有的行

            System.out.println(clos + " rows:" + rows);
            for (int i = 1; i < rows; i++) {
                for (int j = 0; j < clos; j++) {
                    //第一个是列数，第二个是行数
                    String id = rs.getCell(j++, i).getContents();//默认最左边编号也算一列 所以这里得j++
                    String name = rs.getCell(j++, i).getContents();
                    String sex = rs.getCell(j++, i).getContents();
                    String num = rs.getCell(j++, i).getContents();

                    System.out.println("id:" + id + " name:" + name + " sex:" + sex + " num:" + num);
//                    list.add(new Book(Integer.parseInt(id), name, sex, Integer.parseInt(num)));
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;

    }

    public static List<Book> getExcel(String file) {
        int i;
        Sheet sheet;
        Workbook book;
        Cell cell1, cell2, cell3, cell4, cell5;
        List<Book> result = new ArrayList<>();
        try {
            //t.xls为要读取的excel文件名
            book = Workbook.getWorkbook(new File(file));

            //获得第一个工作表对象(ecxel中sheet的编号从0开始,0,1,2,3,....)
            sheet = book.getSheet(1);
            //获取左上角的单元格
            cell1 = sheet.getCell(0, 0);
            System.out.println("标题：" + cell1.getContents());

            i = 1;
            while (true) {
                //获取每一行的单元格
                cell1 = sheet.getCell(0, i);//（列，行）
                cell2 = sheet.getCell(1, i);
                cell3 = sheet.getCell(2, i);
                cell4 = sheet.getCell(3, i);
                cell5 = sheet.getCell(4, i);
                if ("".equals(cell1.getContents()) == true)  //如果读取的数据为空
                    break;
                System.out.println(cell1.getContents() + "\t" + cell2.getContents() + "\t" + cell3.getContents() + "\t"
                        + cell4.getContents() + "\t" + cell5.getContents());
                Book book1 = new Book();
                book1.setNo(cell2.getContents());
                book1.setName(cell3.getContents());
                switch (cell4.getContents()) {
                    case "文学":
                book1.setClassId(1L);
                        break;
                    case "技术":
                        book1.setClassId(6L);
                        break;
                    case "管理":
                        book1.setClassId(7L);

                        break;
                    case "金融":
                        book1.setClassId(8L);
                        break;
                    default:
                        System.out.printf(">>>>>>>>>>>" + cell4.getContents());
                        break;
                }
                book1.setClassName(cell4.getContents());
                book1.setCreateTime(new Date());
                book1.setQuantity(1);
                book1.setAuthor("未知");
                book1.setIntroduction("暂无介绍");
                result.add(book1);
                i++;
            }
            book.close();
        } catch (Exception e) {
            System.out.printf(">>>>>>>>>>>" + e.getMessage());
        }
        return result;
    }
}
