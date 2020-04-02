/*
 * bangkom-service
 *
 * Copyright (c) 2019
 * All rights reserved
 * Written by od3ng created on 8/30/19, 10:43 AM
 * Blog    : sinaungoding.com
 * Email   : noprianto@sinaungoding.com,lepengdados@gmail.com
 * Github  : 0d3ng
 * Hp      : 085878554150
 */

package com.sinaungoding.parking.controller.exception;

import id.go.lan.bangkomservice.util.response.AuthResponse;
import lombok.Data;

@Data
public class ClientException extends RuntimeException {
    private ResponseApi status;

    public ClientException(String message) {
        super(message);
    }

    public ClientException(String message, ResponseApi status) {
        super(message);
        this.status = status;
    }
}
