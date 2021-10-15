package com.bandtec.br.finoban.repository.database;

import com.bandtec.br.finoban.dominio.DAO.*;
import com.bandtec.br.finoban.dominio.entidades.Acesso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DashboardRepository extends JpaRepository<Acesso, Integer> {

    @Query(
            value = "select data_hora_saida as 'dataHoraSaida', renda from acesso, cliente " +
                    "where fk_cliente = id_cliente and data_hora_saida >= date_sub(now(),interval 30 day)",
            nativeQuery = true
    )
    List<RendimentoMensalDAO> getRendimentoMensalData();

    @Query(
//            value = "select status_saida as 'statusSaida', count(*) as 'contagem' from cliente, acesso " +
//                    "where fk_cliente = id_cliente and data_hora_saida >= date_sub(now(),interval 15 day) " +
//                    "group by status_saida",
            value = "select status_saida as 'statusSaida', count(*) as 'contagem' from cliente, acesso " +
                    "where fk_cliente = id_cliente " +
                    "group by status_saida",
            nativeQuery = true
    )
    List<PorcentualPerdasDAO> getPorcentualPerdaData();

    @Query(
            value = "select data_hora_entrada as 'dataHoraEntrada', data_hora_saida as 'dataHoraSaida' " +
                    "from acesso, cliente where fk_cliente = id_cliente",
            nativeQuery = true
    )
    List<TempoPermanenciaDAO> getTempoPermanenciaData();

    @Query(
            value = "select aval_positivo as 'avalPositivo', count(*) as 'contagem' " +
                    "from avaliacao group by aval_positivo",
            nativeQuery = true
    )
    List<AvaliacaoSiteDAO> getAvaliacaoSiteData();

    @Query(
            value = "select descricao_regiao as 'descricaoRegiao', valor_regiao as 'valorRegiao', renda " +
                    "from acesso, regiao where fk_regiao = id_regiao",
            nativeQuery = true
    )
    List<RegiaoRendaDAO> getRegiaoRendaData();

    @Query(
            value = "select valor_imovel as 'valorImovel', data_nasc as 'dataNasc' " +
                    "from cliente, acesso where id_cliente = fk_cliente",
            nativeQuery = true
    )
    List<ValorImovelIdadeDAO> getValorImovelIdadeData();

    @Query(
            value = "select valor_imovel as 'valorImovel', renda from acesso",
            nativeQuery = true
    )
    List<ValorImovelRendaDAO> getValorImovelRendaData();

    @Query(
            value = "select bairro as 'bairroCliente', descricao_regiao as 'regiaoEscolhida' " +
                    "from acesso, cliente, regiao where fk_cliente = id_cliente and fk_regiao = id_regiao",
            nativeQuery = true
    )
    List<CepRegiaoEscolhidaDAO> getCepRegiaoData();

    @Query(
            value = "select descricao_regiao as 'descricaoRegiao', count(*) as 'contagem' " +
                    "from acesso, regiao where fk_regiao = id_regiao group by descricao_regiao;",
            nativeQuery = true
    )
    List<RegioesEscolhidasDAO> getRegioesEscolhidasData();

    @Query(
            value = "select banco_escolhido as 'bancoEscolhido', count(*) as 'contagem' " +
                    "from acesso group by banco_escolhido",
            nativeQuery = true
    )
    List<BancosEscolhidosDAO> getBancosEscolhidosData();
}
