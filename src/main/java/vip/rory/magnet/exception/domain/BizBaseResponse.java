package vip.rory.magnet.exception.domain;

import java.io.Serializable;

import vip.rory.magnet.enums.BizEnum;
import vip.rory.magnet.enums.BizErrorCodeEnum;

public class BizBaseResponse implements Serializable {

    private static final long serialVersionUID = 6657433146076866328L;

    private int               code;
    private String            message;
    private Object            data;

    public BizBaseResponse() {
        this(BizErrorCodeEnum.SUCCESS, BizErrorCodeEnum.SUCCESS.getDesc());
    }

    public BizBaseResponse(BizEnum errorCode) {
        this(errorCode, errorCode.getDesc());
    }

    public BizBaseResponse(Object data) {
        this(BizErrorCodeEnum.SUCCESS, BizErrorCodeEnum.SUCCESS.getDesc(), data);
    }

    public BizBaseResponse(BizEnum errorCode, String message) {
        this(errorCode, message, null);
    }

    public BizBaseResponse(BizEnum errorCode, String message, Object data) {
        this.code = errorCode.getCode();
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    /**
     * 判断是否成功
     * 
     * @return 成功返回<code>true</code>，否则返回<code>false</code>
     */
    public boolean isSuccess() {
        return this.code == BizErrorCodeEnum.SUCCESS.getCode();
    }

    public static BizBaseResponse success() {
        return new BizBaseResponse();
    }

    public static BizBaseResponse success(String message) {
        return new BizBaseResponse(BizErrorCodeEnum.SUCCESS, message);
    }

    public static BizBaseResponse success(Object data) {
        return new BizBaseResponse(data);
    }

    public static BizBaseResponse success(String message, Object data) {
        return new BizBaseResponse(BizErrorCodeEnum.SUCCESS, message, data);
    }

    public static BizBaseResponse operationFailed() {
        return new BizBaseResponse(BizErrorCodeEnum.OPERATION_FAILED);
    }

    public static BizBaseResponse operationFailed(String message) {
        return new BizBaseResponse(BizErrorCodeEnum.OPERATION_FAILED, message);
    }

    public static BizBaseResponse operationFailed(Object data) {
        return new BizBaseResponse(BizErrorCodeEnum.OPERATION_FAILED, BizErrorCodeEnum.OPERATION_FAILED.getDesc(),
                data);
    }

    public static BizBaseResponse operationFailed(String message, Object data) {
        return new BizBaseResponse(BizErrorCodeEnum.OPERATION_FAILED, message, data);
    }

}
