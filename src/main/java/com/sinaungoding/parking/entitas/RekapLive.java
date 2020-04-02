package com.sinaungoding.parking.entitas;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class RekapLive {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(length = 40)
    private String id;
    private int terisi;
    private int sisa;
    @Temporal(TemporalType.TIME)
    private Date catat;
}
