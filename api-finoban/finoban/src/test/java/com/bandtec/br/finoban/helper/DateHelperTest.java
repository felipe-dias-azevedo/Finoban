package com.bandtec.br.finoban.helper;

import com.bandtec.br.finoban.infraestrutura.helpers.DateHelper;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.*;

public class DateHelperTest {

    @Test
    public void converterStringISO8601ParaDateFunciona() {
        Date date = DateHelper.obterDataAgoraUTC();
        String dateString = DateHelper.converterDataPadraoISO8601(date);
        assertEquals(
                date.toInstant()
                        .atOffset(ZoneOffset.UTC)
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                DateHelper.converterStringPadraoISO8601ParaDate(dateString).toInstant()
                        .atOffset(ZoneOffset.UTC)
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    }

    @Test
    public void converterDataParaPadraoISOFuncionaDate() {
        LocalDateTime localDateTime = LocalDateTime.of(
                2021, 10, 27, 20, 11);
        Date date = Date.from(localDateTime.atZone(ZoneId.of("UTC")).toInstant());
        assertEquals("2021-10-27T20:11Z", DateHelper.converterDataPadraoISO8601(date));
    }

    @Test
    public void converterDataParaPadraoISOFuncionaLocalDateTime() {

        LocalDateTime localDateTime = LocalDateTime.of(
                1998, 1, 31, 23, 59);
        assertEquals("1998-01-31T23:59Z", DateHelper.converterDataPadraoISO8601(localDateTime));
    }

    @Test
    public void converterDataParaPadraoISOAgoraFuncionaDate() {
        Date date = new Date();
        assertNotNull(DateHelper.obterDataAgoraPadraoISO8601());
        assertEquals(
                DateHelper.converterDataPadraoISO8601(date),
                DateHelper.obterDataAgoraPadraoISO8601());
    }

    @Test
    public void converterDataParaPadraoISOAgoraFuncionaDateFromLocalDateTime() {
        Date date = Date.from(LocalDateTime.now(ZoneOffset.UTC).toInstant(ZoneOffset.UTC));
        assertNotNull(DateHelper.obterDataAgoraPadraoISO8601());
        assertEquals(
                DateHelper.converterDataPadraoISO8601(date),
                DateHelper.obterDataAgoraPadraoISO8601());
    }

    @Test
    public void converterDataParaPadraoISOAgoraFuncionaLocalDateTime() {
        LocalDateTime localDateTime = LocalDateTime.now(ZoneOffset.UTC);
        assertNotNull(DateHelper.obterDataAgoraPadraoISO8601());
        assertEquals(
                DateHelper.converterDataPadraoISO8601(localDateTime),
                DateHelper.obterDataAgoraPadraoISO8601());
    }
}
