package com.players.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NoSuchPlayerException extends RuntimeException {
    public NoSuchPlayerException(String msg) {
        super(msg);
    }
}
