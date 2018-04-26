package util;

import cn.edu.sdjzu.xg.xkgl.domain.Course;
import cn.edu.sdjzu.xg.xkgl.domain.CourseType;
import cn.edu.sdjzu.xg.xkgl.domain.Teacher;
import cn.edu.sdjzu.xg.xkgl.service.CourseService;
import cn.edu.sdjzu.xg.xkgl.service.CourseTypeService;
import cn.edu.sdjzu.xg.xkgl.service.TeacherService;
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

public class ExcleInportCourse {

    public static void inportCourse(String fileName, HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {

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
                    r.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                    String no = r.getCell(0).getStringCellValue();

                    String title = r.getCell(1).getStringCellValue();

                    /*r.getCell(2).setCellType(Cell.CELL_TYPE_STRING);*/
                    int max = r.getCell(2).getColumnIndex();

                    /*r.getCell(3).setCellType(Cell.CELL_TYPE_NUMERIC);*/
                    int min = r.getCell(3).getColumnIndex();

                    /*r.getCell(4).setCellType(Cell.CELL_TYPE_STRING);*/
                    int hours = r.getCell(4).getColumnIndex();

                    String time = r.getCell(5).getStringCellValue();

                    r.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
                    int credit = r.getCell(6).getColumnIndex();

                    r.getCell(7).setCellType(Cell.CELL_TYPE_STRING);
                    String teacher_no = r.getCell(7).getStringCellValue();
                    Teacher teacher = TeacherService.getInstance().findByNo(teacher_no);

                    r.getCell(8).setCellType(Cell.CELL_TYPE_STRING);
                    String courseType_des = r.getCell(8).getStringCellValue();
                    CourseType courseType = (CourseType) CourseTypeService.getInstance().findByDes(courseType_des);


                    //创建实体类
                    Course course = new Course(no,title,max,min,0,hours,time,credit,false,teacher,courseType);
                    CourseService.getInstance().add(course);
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
