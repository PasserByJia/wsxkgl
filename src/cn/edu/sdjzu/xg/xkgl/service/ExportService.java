package cn.edu.sdjzu.xg.xkgl.service;

import cn.edu.sdjzu.xg.xkgl.domain.Student;
import cn.edu.sdjzu.xg.xkgl.domain.message.Result;
import org.apache.poi.hssf.usermodel.*;
import util.Utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;


public class ExportService {
    private static ExportService exportService = new ExportService();

    private ExportService(){}

    public static ExportService getInstance(){
        return exportService;
    }
    //    final Logger logger = LoggerFactory.getLogger(ExportService.class);
    public Result exportExcel(List<Student> studentList,
                              HttpServletResponse response,
                              HttpServletRequest request) throws IOException {
        //创建一个workbook， 相当于一个excel
        HSSFWorkbook workbook = new HSSFWorkbook();
        //在workbook中创建一个sheet，相当于excel的sheet
        HSSFSheet sheet = workbook.createSheet("学生信息");
        //在sheet中添加表头第0行
        HSSFRow row = sheet.createRow(0);
        //设置表头单元格居中
        HSSFCellStyle style = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 12);
        style.setFont(font);

        //表头数组
        String[] title = {"学号" ,"姓名"};

        HSSFCell cell = null;
        //设置表头列值
        for (int i = 0; i < 2; i++) {
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(style);
        }

        //添加员工信息，每一行代表一个员工
        for (int i = 0; i < studentList.size(); i++) {
            row = sheet.createRow(i + 1);
            Student student = studentList.get(i);
            //设置每个单元格的值和样式
            HSSFCell cell0 = row.createCell(0);
            if(student.getNo() != null){
                cell0.setCellValue(student.getNo());
            }
            cell0.setCellStyle(style);
            HSSFCell cell1 = row.createCell(1);
            if(student.getName() != null){
                cell1.setCellValue(student.getName());
            }
            cell1.setCellStyle(style);
        }

        //为每一列设置自动宽度
        for (int i = 0; i < 2; i++) {
            sheet.autoSizeColumn(i);
        }

        Result result = new Result();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        workbook.write(byteArrayOutputStream);
        byteArrayOutputStream.flush();

        Utils.download(byteArrayOutputStream,response,request,"student.xls");

        return result;
    }

}
