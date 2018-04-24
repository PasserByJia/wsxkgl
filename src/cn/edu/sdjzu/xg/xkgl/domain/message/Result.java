package cn.edu.sdjzu.xg.xkgl.domain.message;

import java.io.Serializable;

public class Result implements Serializable {
    //用于判断操作是否成功
    private boolean success = false;
    //提示信息
    private String msg = "导出成功";

    public boolean isSuccess(){return success;}

    public void setSuccess(boolean success){this.success = success;}
    public String getMsg(){return msg;}
    public void setMsg(String msg){this.msg = msg;}
}
