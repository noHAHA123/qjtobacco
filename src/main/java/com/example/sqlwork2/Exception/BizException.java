package com.example.sqlwork2.Exception;

import com.example.sqlwork2.enums.BizError;
import com.example.sqlwork2.enums.Error;

public class BizException extends RuntimeException {

    protected final Error error;

    public BizException(Error error) {
        super(error.getMessage());
        this.error = error;
    }
//
//    public BizException(final String message) {
//        super(message);
//        this.error = BizError.UNSPECIFIED;
//    }
//
//    public BizException(final Throwable t) {
//        super(t);
//        this.error = BizError.UNSPECIFIED;
//    }
}
