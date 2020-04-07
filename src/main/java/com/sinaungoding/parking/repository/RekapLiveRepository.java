/*
 * polinema-parking-service
 *
 * Copyright (c) 2020
 * All rights reserved
 * Written by od3ng created on 4/3/20, 1:07 AM
 * Blog    : sinaungoding.com
 * Email   : noprianto@polinema.ac.id
 * Github  : 0d3ng
 * Hp      : 085878554150
 */

package com.sinaungoding.parking.repository;

import com.sinaungoding.parking.entitas.RekapLive;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface RekapLiveRepository extends JpaRepository<RekapLive, String> {
    List<RekapLive> findAllByTanggal(Date tanggal);

    List<RekapLive> findByTanggalEquals(Date date);

    List<RekapLive> findAllByTanggalBetween(Date start, Date end);

    List<RekapLive> findByTanggalIs(Date date);

}
