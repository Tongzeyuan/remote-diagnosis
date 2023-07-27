package com.wzu.remote.common.utils;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.wzu.remote.common.result.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResponseUtil {

    /**
     * 通过HttpServletResponse返回响应数据
     * @param response
     * @param r
     */
    public static void out(HttpServletResponse response, Result r){
        ObjectMapper mapper = new ObjectMapper();
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        try{
            mapper.writeValue(response.getWriter(),r);
        }catch (IOException e){ e.printStackTrace();  }
    }
}
