package com.owner.me.mytest;

import java.time.*;
import java.util.Date;

public class LocalDateTimeTest {
    public static void main(String[] args){
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDate);
        System.out.println(localTime);
        System.out.println(localDateTime);

        localDate = LocalDate.of(2020,02,02);
        System.out.println(localDate);

        localDate.plusYears(1);
        System.out.println(localDate.plusYears(1));
        System.out.println(localDate.withDayOfYear(22));
        System.out.println(localDate.withYear(2020));

        System.out.println(localDate.isAfter(localDate));

        System.out.println(Clock.systemUTC());
        System.out.println(Clock.systemDefaultZone());

        System.out.println(Instant.now());

        Date date = Date.from(Instant.now());
        System.out.println(date);
        System.out.println(date.toInstant());

        Period period = Period.between(LocalDate.of(2020,1,1),LocalDate.now());
        System.out.println(period.getDays());

        Duration duration = Duration.between(localDateTime,localDateTime);
        System.out.println(duration.getSeconds());
    }
}
