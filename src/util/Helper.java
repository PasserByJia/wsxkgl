package util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by dell on 2016-11-18 .
 */
public class Helper {
    public static int getIdFromRequest(HttpServletRequest request) {
        return getIdFromRequest(request,"id");
    }
    public static int getIdFromRequest(HttpServletRequest request,String fieldName) {
        String fieldPara = request.getParameter(fieldName);
        return Integer.parseInt(fieldPara);
    }
    /**
     * @param logicView 逻辑视图
     * @return 物理视图
     */
    public static String resolve(String logicView){
        return "/WEB-INF/pages/"+logicView+".jsp";
    }
}
