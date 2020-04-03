/*
 * polinema-parking-service
 *
 * Copyright (c) 2020
 * All rights reserved
 * Written by od3ng created on 4/3/20, 1:00 AM
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
import com.sinaungoding.parking.dto.MahasiswaDto;
import com.sinaungoding.parking.entitas.Mahasiswa;
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
@RequestMapping("/api/v1/mahasiswa")
public class MahasiswaController {
    @Autowired
    private MahasiswaRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(CREATED)
    public MahasiswaDto simpan(@Valid @RequestBody MahasiswaDto dto) throws Exception {
        try {
            Mahasiswa mahasiswa = modelMapper.map(dto, Mahasiswa.class);
            repository.save(mahasiswa);
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

    @GetMapping("/{nim}")
    public MahasiswaDto getMahasiswaDto(@PathVariable("nim") String nim) throws Exception {
        try {
            Mahasiswa mahasiswa = repository.findById(nim).orElseThrow(() -> new NotFoundException(nim));
            MahasiswaDto dto = modelMapper.map(mahasiswa, MahasiswaDto.class);
            return dto;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }

    @GetMapping
    public Page<MahasiswaDto> getMahasiswaDtos(Pageable pageable) throws Exception {
        try {
            Page<Mahasiswa> all = repository.findAll(pageable);
            List<MahasiswaDto> mahasiswaDtos = new ArrayList<>();
            all.forEach(mahasiswa -> {
                MahasiswaDto dto = modelMapper.map(mahasiswa, MahasiswaDto.class);
                mahasiswaDtos.add(dto);
            });
            return new PageImpl<>(mahasiswaDtos, pageable, all.getTotalElements());
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }

    @PatchMapping("/{nim}")
    @ResponseStatus(OK)
    public void update(@PathVariable("nim") String nim, @Valid @RequestBody MahasiswaDto dto) throws Exception {
        try {
            Mahasiswa mahasiswa1 = repository.findById(nim).orElseThrow(() -> new NotFoundException(nim));

            Mahasiswa mahasiswa = modelMapper.map(dto, Mahasiswa.class);
            mahasiswa.setNim(nim);
            BeanUtils.copyProperties(mahasiswa, mahasiswa1);
            repository.save(mahasiswa1);
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
    public void delete(@PathVariable("nim") String nim) throws Exception {
        try {
            Mahasiswa mahasiswa1 = repository.findById(nim).orElseThrow(() -> new NotFoundException(nim));
            repository.deleteById(nim);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new InternalServerErrorException(e.getMessage());
        }
    }
}
