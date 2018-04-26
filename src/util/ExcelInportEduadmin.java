package util;

import cn.edu.sdjzu.xg.xkgl.domain.EduAdmin;
import cn.edu.sdjzu.xg.xkgl.service.EduAdminService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;

public class ExcelInportEduadmin {

    public static void inportEduadmin(String fileName, HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {

        FileInputStream fileIn = new FileInputStream(new File("E:\\"+fileName));
        //根据指定的文件输入流导入Excel从而产生Workbook对象
        Workbook wb0 = new XSSFWorkbook(fileIn);

        //获取Excel文档中的第一个表单
        Sheet sht0 = wb0.getSheetAt(0);
        //对Sheet中的每一行进行迭代
        for (Row r : sht0) {

            //如果当前行的行号（从0开始）未达到2（第三行）则从新循环
            if(r.getRowNum()<1){
                continue;
            }

            if(checkRowNull(r)==1) {
                break;
            } else{
                try {
                    //取出当前行第1个单元格数据，并封装在name属性上
                    String name = r.getCell(0).getStringCellValue();

                    r.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                    String no = r.getCell(1).getStringCellValue();

                    String sex = r.getCell(2).getStringCellValue();

                    //创建实体类
                    EduAdmin eduAdmin = new EduAdmin("123456", no, name, no, sex);
                    EduAdminService.getInstance().add(eduAdmin);
                } catch(SQLException e){
                    request.setAttribute("message","导入失败");
                    request.getRequestDispatcher("/pages/error.jsp").forward(request,response);
                }
            }
        }
        fileIn.close();

        request.setAttribute("message","导入成功");
        request.getRequestDispatcher("/pages/error.jsp").forward(request,response);
    }

    private static int checkRowNull(Row r) {
        int num = 1;
        Iterator<Cell> cellItr =r.iterator();
        while(cellItr.hasNext()){
            Cell c =cellItr.next();
            if(c.getCellType() != 0){
                num--;
            }
        }
        return num;
    }
}
