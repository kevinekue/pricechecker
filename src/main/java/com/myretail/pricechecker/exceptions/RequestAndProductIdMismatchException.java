package com.myretail.pricechecker.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Kevin Ekue created on 3/24/2021
 */

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RequestAndProductIdMismatchException extends RuntimeException{
    public RequestAndProductIdMismatchException(String errorMessage){
        super("RequestAndProductIdMismatchException: " + errorMessage);
    }
}
