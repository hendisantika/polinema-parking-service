/*
 * aplikasi-akademik
 *
 * Copyright (c) 2019
 * All rights reserved
 * Written by od3ng created on 8/14/19, 1:33 PM
 * Blog    : sinaungoding.com
 * Email   : noprianto@sinaungoding.com,lepengdados@gmail.com
 * Github  : 0d3ng
 * Hp      : 085878554150
 */

package com.sinaungoding.parking.controller.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String id) {
        super(String.format("%s not found", id));
    }
}
