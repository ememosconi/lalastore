package com.alalala.msseed.adapter.rest.exception;


import com.alalala.msseed.config.ErrorCode;
import com.alalala.msseed.config.GenericException;

public final class TimeoutRestClientException extends GenericException {

    public TimeoutRestClientException(ErrorCode errorCode) {
        super(errorCode);
    }

}
