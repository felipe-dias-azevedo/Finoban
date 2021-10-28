package com.bandtec.br.finoban.infraestrutura.helpers;

import javax.xml.bind.DatatypeConverter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

public class DateHelper {

    public static boolean verificarJwtExpirou(long exp) {
        Instant instant = Instant.ofEpochSecond(exp);
        Date date = Date.from(instant);
        LocalDateTime localDate = LocalDateTime.now();
        Date date1 = Date.from(localDate.atZone(ZoneId.systemDefault()).toInstant());
        return date.before(date1);
    }

    public static Date converterStringPadraoISO8601ParaDate(String date) {
        return Date.from(Instant.from(DateTimeFormatter.ISO_DATE_TIME.parse(date)));
    }

    public static Date obterDataAgoraUTC() {
        return Date.from(LocalDateTime.now(ZoneOffset.UTC).toInstant(ZoneOffset.UTC));
    }

    public static String obterDataAgoraPadraoISO8601() {
        return converterDataPadraoISO8601(obterDataAgoraUTC());
    }

    public static String converterDataPadraoISO8601(LocalDateTime localDateTime) {
        return converterDataPadraoISO8601(
                Date.from(localDateTime.atOffset(ZoneOffset.UTC).toInstant()));
    }

    public static String converterDataPadraoISO8601(Date date) {
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
        df.setTimeZone(tz);
        return df.format(date);
    }
}
