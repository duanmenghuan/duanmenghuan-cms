package com.duanmenghuan.bean;

import java.io.Serializable;

public class ResultMsg implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 3732818067228914762L;
    /*result 处理的结果
     * errorMsg错误消息
     * data 返回的具体数据
     */
    int result;
    String errorMsg;
    Object data;

    public ResultMsg() {
        super();
        // TODO Auto-generated constructor stub
    }

    public ResultMsg(int result, String errorMsg, Object data) {
        super();
        this.result = result;
        this.errorMsg = errorMsg;
        this.data = data;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


}
