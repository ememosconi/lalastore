package com.alalala.msseed.adapter.rest.exception;


import com.alalala.msseed.config.ErrorCode;
import com.alalala.msseed.config.GenericException;

public final class NonTargetRestClientException extends GenericException {

    public NonTargetRestClientException(ErrorCode errorCode) {
        super(errorCode);
    }

}
