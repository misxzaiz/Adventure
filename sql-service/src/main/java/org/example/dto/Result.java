package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private Boolean success;
    private String message;
    private String errorMsg;
    private Object data;
    private Long total;


    public static Result ok(){
        return new Result(true,null,null,null,null);
    }
    public static Result ok(String message){
        return new Result(true,message,null,null,null);
    }
    public static Result ok(Object data){
        return new Result(true,null,null,data,null);
    }
    public static Result ok(Object data,String message){
        return new Result(true,message,null,data,null);
    }
    public static Result ok(List<?> data, Long total){
        return new Result(true,null,null,data,total);
    }
    public static Result fail(String errorMsg){
        return new Result(true,null,errorMsg,null,null);
    }
    public static Result fail(Object data,String message,String errorMsg){
        return new Result(true,message,errorMsg,data,null);
    }
    public static Result fail(String message,String errorMsg){
        return new Result(true,message,errorMsg,null,null);
    }
}
