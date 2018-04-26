package util;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.*;

public class FileUpload {

    public static String fileUpload(HttpServletRequest request) throws ServletException, IOException {
                /* 对上传文件夹和临时文件夹进行初始化 * */
        File tmpDir = null;
        String fileName = null;
        String tmpPath = "E:\\";
        tmpDir = new File(tmpPath);
        request.setCharacterEncoding("utf-8");
        try {
            if (ServletFileUpload.isMultipartContent(request)) {
//                DiskFileItemFactory dff = new DiskFileItemFactory();
                DiskFileItemFactory dff = new DiskFileItemFactory();
                dff.setRepository(tmpDir);// 指定上传文件的临时目录
                dff.setSizeThreshold(1024000);//指定在内存中缓存数据大小,单位为byte
                ServletFileUpload sfu = new ServletFileUpload(dff);//创建该对象
                sfu.setFileSizeMax(5000000);// 指定单个上传文件的最大尺寸
                sfu.setSizeMax(10000000);// 指定一次上传多个文件的 总尺寸
                FileItemIterator fii = sfu.getItemIterator(request);// 解析request 请求, 并返回FileItemIterator集合
                while (fii.hasNext()) {
                    FileItemStream fis = fii.next();//从集合中获得一个文件流
                    if (!fis.isFormField() && fis.getName().length() > 0) {
                        //过滤掉表单中非文件 域
                        fileName = fis.getName();//获得上传文件的文件名
                        BufferedInputStream in = new BufferedInputStream(fis.openStream());//获得文件输入流
                        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(tmpPath + fileName)));//获得文件输出流
                        Streams.copy(in, out, true);
                        //开始把文件写到你指定的上传文件夹
                        in.close();
                        out.close();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileName;
    }
}
