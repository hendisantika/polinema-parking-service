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

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class MahasiswaDto {
    @NotNull
    @NotEmpty
    private String nim;
    @NotNull
    @NotEmpty
    private String nama;
    @NotNull
    @NotEmpty
    private String jurusan;
    @NotNull
    @NotEmpty
    private String prodi;
    private int angkatan;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date tanggalLahir;
    private String jenisKelamin;
    private String email;
    private String hp;
}
