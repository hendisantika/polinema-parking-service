/*
 * bangkom-service
 *
 * Copyright (c) 2019
 * All rights reserved
 * Written by od3ng created on 9/12/19, 9:31 AM
 * Blog    : sinaungoding.com
 * Email   : noprianto@sinaungoding.com,lepengdados@gmail.com
 * Github  : 0d3ng
 * Hp      : 085878554150
 */

package com.sinaungoding.parking.controller.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthServiceException extends Exception {
    private ResponseApi status;
}
