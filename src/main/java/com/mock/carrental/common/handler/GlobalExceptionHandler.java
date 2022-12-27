package com.mock.carrental.common.handler;

import cn.hutool.json.JSONUtil;
import com.mock.carrental.common.base.BaseResponse;
import com.mock.carrental.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(BizException.class)
    public BaseResponse<Void> handleException(BizException e){
        BaseResponse<Void> responseDto = new BaseResponse<>();
        responseDto.setCode(BaseResponse.CODE_ERROR);
        responseDto.setMsg(e.getMessage());
        log.error("EXCEPTION MESSAGE: {}.", e.getMessage());
        return responseDto;
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public BaseResponse<Void> handleException(Exception e){
        BaseResponse<Void> responseDto = new BaseResponse<>();
        responseDto.setCode(BaseResponse.CODE_ERROR);
        responseDto.setMsg(BaseResponse.MSG_ERROR);
        log.error("ERROR MESSAGE: {}.", JSONUtil.toJsonStr(e));
        return responseDto;
    }
}
