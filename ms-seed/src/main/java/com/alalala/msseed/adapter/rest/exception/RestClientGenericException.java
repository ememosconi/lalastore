package com.alalala.msseed.adapter.rest.exception;


import com.alalala.msseed.config.ErrorCode;
import com.alalala.msseed.config.GenericException;

public final class RestClientGenericException extends GenericException {

    public RestClientGenericException(ErrorCode errorCode) {
        super(errorCode);
    }

}
