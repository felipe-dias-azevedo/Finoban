package com.bandtec.br.finoban.helpers;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateHelper {

    public static boolean verificarJwtExpirou(long exp) {
        Instant instant = Instant.ofEpochSecond(exp);
        Date date = Date.from(instant);
        System.out.println(date);
        LocalDateTime localDate = LocalDateTime.now();
        Date date1 = Date.from(localDate.atZone(ZoneId.systemDefault()).toInstant());
        System.out.println("Date = " + date1);
        boolean minutos = date.before(date1);
        System.out.println(minutos);
        return minutos;
    }
}
