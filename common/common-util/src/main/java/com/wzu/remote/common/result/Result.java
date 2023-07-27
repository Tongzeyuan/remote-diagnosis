package com.wzu.remote.common.result;

public class Result<T> {

    //返回码
    private Integer code;

    //返回消息
    private String message;

    //返回数据
    private T data;

    public Result(){}

    //只填充返回数据
    protected static <T> Result<T> build(T data) {
        Result<T> result = new Result<>();
        if(data != null){
            result.setData(data);
        }
        return result;
    }

    //填充返回数据,返回码,返回消息
    public static <T> Result<T> build(T body,Integer code,String message){
        Result<T> result = build(body);
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    //填充返回数据,根据ResultCodeEnum枚举类填充返回码,返回消息
    public static <T> Result<T> build(T body,ResultCodeEnum resultCodeEnum){
        Result<T> result = build(body);
        result.setCode(resultCodeEnum.getCode());
        result.setMessage(resultCodeEnum.getMessage());
        return result;
    }

    //操作成功,没有返回数据
    public static<T> Result<T> ok(){
        return Result.ok(null);
    }

    //操作成功,有返回数据
    public static<T> Result<T> ok(T data){
        return build(data,ResultCodeEnum.SUCCESS);
    }

    //操作失败,没有返回数据
    public static <T> Result<T> fail(){
        return Result.fail(null);
    }

    //操作失败,有返回数据
    public static <T> Result<T> fail(T data){
        return build(data,ResultCodeEnum.FAIL);
    }

    //设置返回消息
    public Result<T> message(String msg){
        this.setMessage(msg);
        return this;
    }

    //设置返回码
    public Result<T> code(Integer code){
        this.setCode(code);
        return this;
    }




    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
