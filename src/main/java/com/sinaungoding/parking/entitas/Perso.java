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

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Perso {
    @Id
    private String serial;
    private String nopol;
    @Temporal(TemporalType.TIMESTAMP)
    private Date tanggal;
    @Temporal(TemporalType.DATE)
    private Date expired;
    private boolean active;
    @ManyToOne
    @JoinColumn(name = "nim")
    private Mahasiswa mahasiswa;
}
