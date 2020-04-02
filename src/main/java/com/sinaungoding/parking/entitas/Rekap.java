package com.sinaungoding.parking.entitas;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
@Entity
public class Rekap {
    @Id
    private String id;
    private String serial;
    private String idKantong;
    @Temporal(TemporalType.TIMESTAMP)
    private Date tanggal;
    private boolean in_out;
    @Temporal(TemporalType.TIMESTAMP)
    private Date catat;
}
