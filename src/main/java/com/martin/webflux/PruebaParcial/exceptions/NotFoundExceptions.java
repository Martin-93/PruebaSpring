package com.martin.webflux.PruebaParcial.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundExceptions extends Exception {
    public static final long serailversionUID =  -771769199470469507L;
    public static final String DESCRIPTION = "not found";

    public NotFoundExceptions(String message) {
        super(DESCRIPTION+" "+message);
    }
}
