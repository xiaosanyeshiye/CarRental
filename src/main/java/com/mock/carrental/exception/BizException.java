package com.mock.carrental.exception;

/***
 * businessexception
 * @author luw
 */
public class BizException extends RuntimeException {

    /** exception message **/
    private String message;

    public BizException(String message) {
        super(message);
        this.message = message;
    }

    public static BizException create(String message) {
        return new BizException(message);
    }

    @Override
    public String getMessage() {
        return message;
    }
}
