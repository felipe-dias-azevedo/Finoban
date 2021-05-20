package com.bandtec.br.finoban.repository;

import com.bandtec.br.finoban.entidades.Acesso;
import com.bandtec.br.finoban.views.database.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DashboardRepository extends JpaRepository<Acesso, Integer> {

    @Query(
            value = "select data_hora_saida as 'dataHoraSaida', renda from acesso, cliente " +
                    "where fk_cliente = id_cliente and data_hora_saida >= date_sub(now(),interval 30 day)",
            nativeQuery = true
    )
    List<RendimentoMensalDatabaseView> getRendimentoMensalData();

    @Query(
            value = "select status_saida as 'statusSaida', count(*) as 'contagem' from cliente, acesso " +
                    "where fk_cliente = id_cliente and data_hora_saida >= date_sub(now(),interval 15 day) " +
                    "group by status_saida",
            nativeQuery = true
    )
    List<PorcentualPerdasDatabaseView> getPorcentualPerdaData();

    @Query(
            value = "select data_hora_entrada as 'dataHoraEntrada', data_hora_saida as 'dataHoraSaida' " +
                    "from acesso, cliente where fk_cliente = id_cliente",
            nativeQuery = true
    )
    List<TempoPermanenciaDatabaseView> getTempoPermanenciaData();

    @Query(
            value = "select aval_positivo as 'avalPositivo', count(*) as 'contagem' " +
                    "from avaliacao group by aval_positivo",
            nativeQuery = true
    )
    List<AvaliacaoSiteDatabaseView> getAvaliacaoSiteData();

    @Query(
            value = "select descricao_regiao as 'descricaoRegiao', renda " +
                    "from acesso, regiao where fk_regiao = id_regiao",
            nativeQuery = true
    )
    List<RegiaoRendaDatabaseView> getRegiaoRendaData();

    @Query(
            value = "select data_nasc as 'dataNasc', renda " +
                    "from cliente, acesso where id_cliente = fk_cliente",
            nativeQuery = true
    )
    List<ValorImovelIdadeDatabaseView> getValorImovelIdadeData();

    @Query(
            value = "select valor_imovel as 'valorImovel', renda from acesso",
            nativeQuery = true
    )
    List<ValorImovelRendaDatabaseView> getValorImovelRendaData();

    @Query(
            value = "select cep, descricao_regiao as 'descricaoRegiao' " +
                    "from acesso, cliente, regiao where fk_cliente = id_cliente and fk_regiao = id_regiao",
            nativeQuery = true
    )
    List<CepRegiaoEscolhidaDatabaseView> getCepRegiaoData();

    @Query(
            value = "select descricao_regiao as 'descricaoRegiao', count(*) as 'contagem' " +
                    "from acesso, regiao where fk_regiao = id_regiao group by descricao_regiao;",
            nativeQuery = true
    )
    List<RegioesEscolhidasDatabaseView> getRegioesEscolhidasData();

    @Query(
            value = "select banco_escolhido as 'bancoEscolhido', count(*) as 'contagem' " +
                    "from acesso group by banco_escolhido",
            nativeQuery = true
    )
    List<BancosEscolhidosDatabaseView> getBancosEscolhidosData();
}
