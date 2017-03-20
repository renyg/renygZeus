package com.shining3d.zeus.exception;

public class BizException extends RuntimeException {

	private static final long serialVersionUID = -7503561679352380338L;

    private String code;

	public BizException() {
        super();
    }

    public BizException(String code,String message) {
        super(message);
        this.code = code;
    }


    public BizException(String code) {
	    this.code = code;
	}
    
    public BizException(Throwable exception) {
        super(exception);
    }
    

    public BizException(String message, Throwable exception) {
        super(message, exception);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
