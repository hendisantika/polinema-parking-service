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
import com.sinaungoding.parking.dto.PersoDto;
import com.sinaungoding.parking.entitas.Perso;
import com.sinaungoding.parking.repository.KantongRepository;
import com.sinaungoding.parking.repository.PersoRepository;
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
@RequestMapping("/api/v1/perso")
public class PersoController {
    @Autowired
    private PersoRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(CREATED)
    public PersoDto simpan(@Valid @RequestBody PersoDto dto) throws Exception {
        try {
            Perso perso = modelMapper.map(dto, Perso.class);
            repository.save(perso);
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

    @GetMapping("/{serial}")
    public PersoDto getMahasiswaDto(@PathVariable("serial") String serial) throws Exception {
        try {
            Perso perso = repository.findById(serial).orElseThrow(() -> new NotFoundException(serial));
            PersoDto dto = modelMapper.map(perso, PersoDto.class);
            return dto;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }

    @GetMapping
    public Page<PersoDto> getMahasiswaDtos(Pageable pageable) throws Exception {
        try {
            Page<Perso> all = repository.findAll(pageable);
            List<PersoDto> mahasiswaDtos = new ArrayList<>();
            all.forEach(perso -> {
                PersoDto dto = modelMapper.map(perso, PersoDto.class);
                mahasiswaDtos.add(dto);
            });
            return new PageImpl<>(mahasiswaDtos, pageable, all.getTotalElements());
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }

    @PatchMapping("/{serial}")
    @ResponseStatus(OK)
    public void update(@PathVariable("serial") String serial, @Valid @RequestBody PersoDto dto) throws Exception {
        try {
            Perso kantong1 = repository.findById(serial).orElseThrow(() -> new NotFoundException(serial));

            Perso perso = modelMapper.map(dto, Perso.class);
            perso.setSerial(serial);
            BeanUtils.copyProperties(perso, kantong1);
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

    @DeleteMapping
    @ResponseStatus(OK)
    public void delete(@PathVariable("serial") String serial) throws Exception {
        try {
            Perso mahasiswa1 = repository.findById(serial).orElseThrow(() -> new NotFoundException(serial));
            repository.deleteById(serial);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new InternalServerErrorException(e.getMessage());
        }
    }
}
