package com.wzu.remote.common.config.exception;

import io.swagger.models.auth.In;
import lombok.Data;

@Data
public class CustomException extends RuntimeException{

    private Integer code;

    public CustomException(Integer code,String message){
        super(message);
        this.code = code;
    }
}
