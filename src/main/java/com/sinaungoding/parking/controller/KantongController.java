/*
 * polinema-parking-service
 *
 * Copyright (c) 2020
 * All rights reserved
 * Written by od3ng created on 4/3/20, 1:59 AM
 * Blog    : sinaungoding.com
 * Email   : noprianto@polinema.ac.id
 * Github  : 0d3ng
 * Hp      : 085878554150
 */

package com.sinaungoding.parking.controller;

import com.sinaungoding.parking.controller.exception.ApiException;
import com.sinaungoding.parking.controller.exception.InternalServerErrorException;
import com.sinaungoding.parking.controller.exception.NotFoundException;
import com.sinaungoding.parking.controller.exception.ResponseApi;
import com.sinaungoding.parking.dto.KantongDto;
import com.sinaungoding.parking.entitas.Kantong;
import com.sinaungoding.parking.repository.KantongRepository;
import com.sinaungoding.parking.repository.MahasiswaRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@Slf4j
@RequestMapping("/api/v1/kantong")
public class KantongController {
    @Autowired
    private KantongRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(CREATED)
    public KantongDto simpan(@Valid @RequestBody KantongDto dto) throws Exception {
        try {
            Kantong kantong = modelMapper.map(dto, Kantong.class);
            repository.save(kantong);
            return dto;
        } catch (Exception e) {
            Throwable cause, resultCause = e;
            while ((cause = resultCause.getCause()) != null && resultCause != cause) {
                resultCause = cause;
            }
            if (resultCause instanceof SQLIntegrityConstraintViolationException) {
                throw new ApiException(new ResponseApi(false, BAD_REQUEST.value(), ((SQLIntegrityConstraintViolationException) resultCause).getMessage()));
            }
            log.error(e.getMessage());
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public KantongDto getMahasiswaDto(@PathVariable("id") String id) throws Exception {
        try {
            Kantong kantong = repository.findById(id).orElseThrow(() -> new NotFoundException(id));
            KantongDto dto = modelMapper.map(kantong, KantongDto.class);
            return dto;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }

    @GetMapping
    public Page<KantongDto> getMahasiswaDtos(Pageable pageable) throws Exception {
        try {
            Page<Kantong> all = repository.findAll(pageable);
            List<KantongDto> mahasiswaDtos = new ArrayList<>();
            all.forEach(kantong -> {
                KantongDto dto = modelMapper.map(kantong, KantongDto.class);
                mahasiswaDtos.add(dto);
            });
            return new PageImpl<>(mahasiswaDtos, pageable, all.getTotalElements());
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }

    @PatchMapping("/{id}")
    @ResponseStatus(OK)
    public void update(@PathVariable("id") String id, @Valid @RequestBody KantongDto dto) throws Exception {
        try {
            Kantong kantong1 = repository.findById(id).orElseThrow(() -> new NotFoundException(id));

            Kantong kantong = modelMapper.map(dto, Kantong.class);
            kantong.setId(id);
            BeanUtils.copyProperties(kantong, kantong1);
            repository.save(kantong1);
        } catch (Exception e) {
            Throwable cause, resultCause = e;
            while ((cause = resultCause.getCause()) != null && resultCause != cause) {
                resultCause = cause;
            }
            if (resultCause instanceof SQLIntegrityConstraintViolationException) {
                throw new ApiException(new ResponseApi(false, BAD_REQUEST.value(), ((SQLIntegrityConstraintViolationException) resultCause).getMessage()));
            }
            log.error(e.getMessage());
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(OK)
    public void delete(@PathVariable("id") String id) throws Exception {
        try {
            Kantong mahasiswa1 = repository.findById(id).orElseThrow(() -> new NotFoundException(id));
            repository.deleteById(id);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new InternalServerErrorException(e.getMessage());
        }
    }
}
