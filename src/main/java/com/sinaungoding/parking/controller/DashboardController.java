/*
 * polinema-parking-service
 *
 * Copyright (c) 2020
 * All rights reserved
 * Written by od3ng created on 4/7/20, 6:35 PM
 * Blog    : sinaungoding.com
 * Email   : noprianto@polinema.ac.id
 * Github  : 0d3ng
 * Hp      : 085878554150
 */

package com.sinaungoding.parking.controller;

import com.sinaungoding.parking.dto.RekapLiveDto;
import com.sinaungoding.parking.entitas.RekapLive;
import com.sinaungoding.parking.repository.RekapLiveRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/v1/dashboard")
public class DashboardController {
    @Autowired
    private RekapLiveRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/findByDate/{date}")
    public List<RekapLiveDto> findByDate(@PathVariable("date")
                                         @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) throws Exception {
        try {
            log.info(date.toString());
            List<RekapLiveDto> dtos = new ArrayList<>();
            List<RekapLive> rekapLiveByTanggal = repository.findAllByTanggal(date);
            log.info(String.format("jumlah %s record", "" + rekapLiveByTanggal.size()));
            if (!rekapLiveByTanggal.isEmpty()) {
                rekapLiveByTanggal.forEach(rekapLive -> {
                    RekapLiveDto dto = modelMapper.map(rekapLive, RekapLiveDto.class);
                    dtos.add(dto);
                });
            }
            return dtos;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new ArrayList<RekapLiveDto>();
    }
}
