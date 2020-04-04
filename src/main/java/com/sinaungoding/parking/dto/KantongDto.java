/*
 * polinema-parking-service
 *
 * Copyright (c) 2020
 * All rights reserved
 * Written by od3ng created on 4/3/20, 12:58 AM
 * Blog    : sinaungoding.com
 * Email   : noprianto@polinema.ac.id
 * Github  : 0d3ng
 * Hp      : 085878554150
 */

package com.sinaungoding.parking.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class KantongDto {
    private String id;
    @NotNull
    @NotEmpty
    private String kode;
    @NotNull
    @NotEmpty
    private String nama;
    private String keterangan;
    @NotNull
    private Integer kapasitas;
}
