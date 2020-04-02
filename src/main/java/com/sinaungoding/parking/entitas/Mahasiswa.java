package com.sinaungoding.parking.entitas;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Mahasiswa {
    @Id
    private String nim;
    private String nama;
    private String jurusan;
    private String prodi;
    private int angkatan;
    @Temporal(TemporalType.DATE)
    private Date tanggalLahir;
    private String jenisKelamin;
    private String email;
    private String hp;
    @OneToMany(mappedBy = "mahasiswa")
    private List<Perso> persos = new ArrayList<>();
}
