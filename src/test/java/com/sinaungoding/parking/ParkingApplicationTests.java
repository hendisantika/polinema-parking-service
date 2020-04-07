package com.sinaungoding.parking;

import com.sinaungoding.parking.entitas.RekapLive;
import com.sinaungoding.parking.repository.RekapLiveRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@SpringBootTest
@Slf4j
class ParkingApplicationTests {

    @Autowired
    RekapLiveRepository repository;

    @Test
    void findByDate() throws Exception {
        Calendar calendar = Calendar.getInstance();
//        List<RekapLive> allByTanggal = repository.findAllByTanggal(new Date());
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2020-04-07");
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, 23);
        calendar.add(Calendar.MINUTE, 55);
//        List<RekapLive> allByTanggal = repository.findAllByTanggalBetween(new SimpleDateFormat("yyyy-MM-dd").parse("2020-04-07"),calendar.getTime());
        List<RekapLive> allByTanggal = repository.findAllByTanggal(calendar.getTime());
        log.info("Hasil " + allByTanggal.size());
    }

    @Test
    void findByRange() throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 5);
        List<RekapLive> allByTanggalBetween = repository.findAllByTanggalBetween(new Date(), calendar.getTime());
        log.info("Hasil " + allByTanggalBetween.size());
    }

    @Test
    void findByDateEquals() throws Exception {
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2020-04-07");
        List<RekapLive> byTanggalEquals = repository.findByTanggalEquals(date);
        log.info(String.format("Hasil equals %s", byTanggalEquals.size()));
    }

    @Test
    void findByTanggal() throws Exception {
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2020-04-07");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 1);
        List<RekapLive> byTanggalEquals = repository.findByTanggalIs(date);
        log.info(String.format("Hasil equals %s", byTanggalEquals.size()));
        byTanggalEquals = repository.findAllByTanggalBetween(date, calendar.getTime());
        log.info(String.format("Hasil equals %s", byTanggalEquals.size()));
    }

}
