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
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RekapLiveRepository extends PagingAndSortingRepository<RekapLive, String> {
}
