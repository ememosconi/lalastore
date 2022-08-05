package com.alalala.msseed.adapter.kafka.exeption;


import com.alalala.msseed.config.ErrorCode;
import com.alalala.msseed.config.GenericException;

public class NotificationException extends GenericException {

    public NotificationException(ErrorCode ec){
        super(ec);
    }
}
