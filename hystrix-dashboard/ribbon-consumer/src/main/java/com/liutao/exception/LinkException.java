package com.liutao.exception;

/**
 * 连接异常
 *
 * @author LIUTAO
 * @version 2017/5/24
 * @see
 * @since
 */
public class LinkException extends Exception {
    private int code;
    public LinkException(String message,int code){
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
