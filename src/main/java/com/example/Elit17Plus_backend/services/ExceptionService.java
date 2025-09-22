package com.example.Elit17Plus_backend.services;

import com.example.Elit17Plus_backend.exceptions.common.ApiException;

public interface ExceptionService {

    void log(ApiException runtimeException);

    void log(RuntimeException runtimeException, int statusCode);
}
