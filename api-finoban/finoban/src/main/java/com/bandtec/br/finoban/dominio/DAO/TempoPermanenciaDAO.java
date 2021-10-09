package com.bandtec.br.finoban.dominio.DAO;

import java.time.LocalDateTime;

public interface TempoPermanenciaDAO {

    LocalDateTime getDataHoraEntrada();
    LocalDateTime getDataHoraSaida();
}
