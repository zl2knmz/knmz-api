package com.knmz.model;

/**
 * ApiResult
 *
 * @author zl
 * @date 2019/7/23 11:35
 */
public class ApiResult {

    /**
     * 状态码：0代表成功，非0即失败
     */
    private int code;

    /**
     * 状态消息：失败时的错误提示
     */
    private String msg;

    /**
     * 数据结果：成功时的响应结果
     */
    private Object result;

    public static ApiResult init4Fail() {
        return new ApiResult(-1, "网络繁忙，请稍后再试。", null);
    }

    public ApiResult() {
    }

    public ApiResult(int code, String msg, Object result) {
        this.code = code;
        this.msg = msg;
        this.result = result;
    }

    public void ok() {
        this.code = 0;
        this.msg = "success";
        this.result = null;
    }

    public void ok(Object result) {
        this.code = 0;
        this.msg = null;
        this.result = result;
    }

    public void fail(String msg) {
        this.code = -1;
        this.msg = msg;
        this.result = null;
    }

    public void fail(int code, String msg) {
        this.code = code;
        this.msg = msg;
        this.result = null;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
