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

package com.sinaungoding.parking.entitas;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Rekap {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(length = 40)
    private String id;
    private String serial;
    private String kode;
    @Temporal(TemporalType.TIMESTAMP)
    private Date tanggal;
    @Column(columnDefinition = "tinyint")
    private boolean in_out;
    @Temporal(TemporalType.TIMESTAMP)
    private Date catat;
}
