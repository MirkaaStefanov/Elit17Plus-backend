package com.example.Elit17Plus_backend.exceptions.token;

import com.example.Elit17Plus_backend.exceptions.common.UnauthorizedException;

public class ExpiredTokenException extends UnauthorizedException {
    public ExpiredTokenException() {
        super("Токенът е изтекъл!");
    }
}
