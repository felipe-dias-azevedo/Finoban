package com.bandtec.br.finoban.dominio.views.database;

import java.time.LocalDateTime;

public interface RendimentoMensalDatabaseView {

    LocalDateTime getDataHoraSaida();
    Double getRenda();
}
