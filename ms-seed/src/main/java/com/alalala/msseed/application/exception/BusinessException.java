package com.alalala.msseed.application.exception;


import com.alalala.msseed.config.ErrorCode;
import com.alalala.msseed.config.GenericException;

public class BusinessException extends GenericException {

    public BusinessException(ErrorCode errorCode){
        super(errorCode);
    }
}
