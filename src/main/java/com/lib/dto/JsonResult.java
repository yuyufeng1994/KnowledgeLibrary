package com.lib.dto;

/**
 * Description:封装Json结果
 */
public class JsonResult<T> {
    private boolean success;
    private T data;
    private String error;

    public JsonResult(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public JsonResult(boolean success,String error) {
        this.success = success;
        this.error = error;
    }

    @Override
    public String toString() {
        return "SeckillResult{" +
                "success=" + success +
                ", data=" + data +
                ", error='" + error + '\'' +
                '}';
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
