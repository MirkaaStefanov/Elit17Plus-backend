package com.example.Elit17Plus_backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class VisitException extends RuntimeException {
    public VisitException(String message) {
        super(message);
    }
}
