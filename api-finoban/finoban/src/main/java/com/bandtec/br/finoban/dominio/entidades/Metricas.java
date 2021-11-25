package com.bandtec.br.finoban.dominio.entidades;

import com.bandtec.br.finoban.dominio.MetricaModel;
import com.bandtec.br.finoban.dominio.UsuarioSimples;
import org.checkerframework.checker.units.qual.C;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Metricas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMetrica;

    @ManyToOne
    private Usuario usuario;

    @Column(name = "nome_banco")
    private String nomeBanco;

    @Column(name = "valor_imovel")
    private double valorImovel;

    public int getIdMetrica() {
        return idMetrica;
    }

    public void setIdMetrica(int idMetrica) {
        this.idMetrica = idMetrica;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Metricas(Usuario usuario, String nomeBanco, double valorImovel) {
        this.usuario = usuario;
        this.nomeBanco = nomeBanco;
        this.valorImovel = valorImovel;
    }

    public Metricas() {
    }

    public String getNomeBanco() {
        return nomeBanco;
    }

    public void setNomeBanco(String nomeBanco) {
        this.nomeBanco = nomeBanco;
    }

    public double getValorImovel() {
        return valorImovel;
    }

    public void setValorImovel(double valorImovel) {
        this.valorImovel = valorImovel;
    }

    public MetricaModel toModel() {
        return new MetricaModel(this.idMetrica, new UsuarioSimples(this.usuario.getId(), this.usuario.getNome()), this.nomeBanco, this.valorImovel);
    }

    public static List<MetricaModel> toList(List<Metricas> metricasList) {
        return metricasList
                .stream()
                .map(metrica -> new MetricaModel(metrica.getIdMetrica(), new UsuarioSimples(metrica.getUsuario().getId(), metrica.getUsuario().getNome()),
                        metrica.getNomeBanco(), metrica.getValorImovel())).collect(Collectors.toList());
    }
}
