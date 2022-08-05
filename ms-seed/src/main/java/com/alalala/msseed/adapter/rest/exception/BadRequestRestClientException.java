package com.alalala.msseed.adapter.rest.exception;


import com.alalala.msseed.config.ErrorCode;
import com.alalala.msseed.config.GenericException;

public final class BadRequestRestClientException extends GenericException {

    public BadRequestRestClientException(ErrorCode errorCode) {
        super(errorCode);
    }
}
