package com.bandtec.br.finoban.infraestrutura.helpers;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateHelper {

    public static boolean verificarJwtExpirou(long exp) {
        Instant instant = Instant.ofEpochSecond(exp);
        Date date = Date.from(instant);
        LocalDateTime localDate = LocalDateTime.now();
        Date date1 = Date.from(localDate.atZone(ZoneId.systemDefault()).toInstant());
        return date.before(date1);
    }
}
