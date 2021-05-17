package com.bandtec.br.finoban.views.database;

import java.time.LocalDateTime;

public interface TempoPermanenciaDatabaseView {

    LocalDateTime getDataHoraEntrada();
    LocalDateTime getDataHoraSaida();
}
