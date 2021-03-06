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

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class RekapDto {
    private String id;
    @NotNull
    @NotEmpty
    private String serial;
    @NotNull
    @NotEmpty
    private String kode;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date tanggalMasuk;
    private Boolean masuk;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date tanggalKeluar;
    private Boolean keluar;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date catat;
}
