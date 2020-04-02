/*
 * bangkom-service
 *
 * Copyright (c) 2019
 * All rights reserved
 * Written by od3ng created on 9/12/19, 9:06 AM
 * Blog    : sinaungoding.com
 * Email   : noprianto@sinaungoding.com,lepengdados@gmail.com
 * Github  : 0d3ng
 * Hp      : 085878554150
 */

package com.sinaungoding.parking.controller.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class CustomErrorResponse {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
}
