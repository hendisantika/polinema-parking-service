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
