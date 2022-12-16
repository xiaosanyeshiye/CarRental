package com.mock.carrental.common.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Accessors(chain = true)
public class BaseResponse<T> {

    public static final Integer CODE_SUCCESS = 200;

    public static final Integer CODE_ERROR = 500;

    public static final String MSG_SUCCESS = "success";

    public static final String MSG_ERROR = "error";

    private Integer code;

    private String msg;

    private T data;

    /**
     * 页码信息
     **/
    private BasePage page;

    public BaseResponse() {
        code = CODE_SUCCESS;
        msg = MSG_SUCCESS;
    }

    public BaseResponse(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BaseResponse(Integer code, String message, T data) {
        this.code = code;
        this.msg = message;
        this.data = data;
    }


    public BaseResponse(Integer code, String message, T data, BasePage page) {
        this.code = code;
        this.msg = message;
        this.data = data;
        this.page = page;
    }

    public BaseResponse(T data) {
        this.code = CODE_SUCCESS;
        this.msg = MSG_SUCCESS;
        this.data = data;
    }

    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(CODE_SUCCESS, MSG_SUCCESS, data);
    }

    public static <T> BaseResponse<T> successMes(String mes) {
        return new BaseResponse<>(CODE_SUCCESS, mes);
    }

    /**
     * 返回成功结果，使用默认提示信息。
     *
     * @param data 结果数据
     * @return Json结果
     */
    public static <T> BaseResponse<T> success(T data, BasePage page) {
        return new BaseResponse<>(CODE_SUCCESS, MSG_SUCCESS, data, page);
    }

    public static <T> BaseResponse<T> success(T data, long pageIndex, long pageSize, long totalPage, long totalCount) {
        BasePage page = new BasePage(pageIndex, pageSize, totalPage, totalCount);
        return new BaseResponse<>(CODE_SUCCESS, MSG_SUCCESS, data, page);
    }


    public static BaseResponse success(String message, Object data) {
        return new BaseResponse(CODE_SUCCESS, message, data);
    }
}
