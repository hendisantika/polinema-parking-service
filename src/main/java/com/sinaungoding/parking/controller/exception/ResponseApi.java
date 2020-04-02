/*
 * bangkom-service
 *
 * Copyright (c) 2019
 * All rights reserved
 * Written by od3ng created on 8/30/19, 10:34 AM
 * Blog    : sinaungoding.com
 * Email   : noprianto@sinaungoding.com,lepengdados@gmail.com
 * Github  : 0d3ng
 * Hp      : 085878554150
 */

package com.sinaungoding.parking.controller.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseApi {
    private boolean success;
    private int code;
    private String message;

    public ResponseApi(boolean success, int code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }
}
