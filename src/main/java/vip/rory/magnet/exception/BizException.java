package vip.rory.magnet.exception;

import java.io.Serializable;

import vip.rory.magnet.enums.BizEnum;
import vip.rory.magnet.enums.BizErrorCodeEnum;

/**
 * BizException
 */
public class BizException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 2728936692069322518L;

    private BizEnum           errorCode;

    private String            errorMessage;

    public BizException() {
        super(BizErrorCodeEnum.OPERATION_FAILED.getDesc());
        this.errorCode = BizErrorCodeEnum.OPERATION_FAILED;
        this.errorMessage = errorCode.getDesc();

    }

    public BizException(BizEnum errorCode) {
        super(errorCode.getDesc());
        this.errorCode = errorCode;
        this.errorMessage = errorCode.getDesc();
    }

    public BizException(BizEnum errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public BizException(BizEnum errorCode, String errorMessage, Throwable exception) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        super.initCause(exception);
    }

    public BizEnum getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorCode(BizEnum errorCode) {
        this.errorCode = errorCode;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
