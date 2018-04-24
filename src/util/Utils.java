package util;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
//import sun.misc.BASE64Encoder;

/**
 * Created by ibs on 16/12/3.
 */
public class Utils {

    /**
     * @param byteArrayOutputStream 将文件内容写入ByteArrayOutputStream
     * @param response HttpServletResponse	写入response
     * @param request HttpServletRequest	写入request
     * @param returnName 返回的文件名
     */
    public static void download(ByteArrayOutputStream byteArrayOutputStream, HttpServletResponse response, HttpServletRequest request, String returnName) throws IOException {
        response.setContentType("application/octet-stream;charset=utf-8");

        returnName = encodeDownloadFilename(returnName,request.getHeader("user-agent"));

        response.addHeader("Content-Disposition",   "attachment;filename=" + returnName);
        response.setContentLength(byteArrayOutputStream.size());

        ServletOutputStream outputstream = response.getOutputStream();	//取得输出流
        byteArrayOutputStream.writeTo(outputstream);					//写到输出流
        byteArrayOutputStream.close();									//关闭
        outputstream.flush();											//刷数据
    }
    /**
     * 下载文件时，针对不同浏览器，进行附件名的编码
     * @param filename 下载文件名
     * @param agent 客户端浏览器
     * @return 编码后的下载附件名
     * @throws IOException
     */
    public static String encodeDownloadFilename(String filename, String agent) throws IOException {
        if(agent.contains("Firefox")){ // 火狐浏览器Firefox
            filename = new String(filename.getBytes("UTF-8"), "ISO-8859-1");
            //filename = "=?UTF-8?B?"+new BASE64Encoder().encode(filename.getBytes("utf-8"))+"?=";
        }else{ // IE及其他浏览器URLEncoder
            filename = URLEncoder.encode(filename,"utf-8");
        }
        return filename;
    }
}
