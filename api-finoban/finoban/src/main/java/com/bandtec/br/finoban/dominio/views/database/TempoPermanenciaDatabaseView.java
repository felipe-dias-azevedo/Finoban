package com.bandtec.br.finoban.dominio.views.database;

import java.time.LocalDateTime;

public interface TempoPermanenciaDatabaseView {

    LocalDateTime getDataHoraEntrada();
    LocalDateTime getDataHoraSaida();
}
