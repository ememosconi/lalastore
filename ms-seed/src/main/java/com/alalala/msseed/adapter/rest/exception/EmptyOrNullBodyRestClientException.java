package com.alalala.msseed.adapter.rest.exception;


import com.alalala.msseed.config.ErrorCode;
import com.alalala.msseed.config.GenericException;

public final class EmptyOrNullBodyRestClientException extends GenericException {

    public EmptyOrNullBodyRestClientException(ErrorCode errorCode) {
        super(errorCode);
    }

}
