package com.bandtec.br.finoban.builder;

import com.bandtec.br.finoban.dominio.entidades.Acesso;
import com.bandtec.br.finoban.dominio.entidades.Regiao;
import com.bandtec.br.finoban.dominio.entidades.Usuario;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AcessoBuilder {


    private Acesso acesso;
    private Usuario usuario;
    private Regiao regiao;

    public AcessoBuilder() {
        acesso = new Acesso();
        usuario = new Usuario();
        regiao = new Regiao();
    }

    public AcessoBuilder setBancoCifra() {
        acesso.setBancoEscolhido("Banco Cifra");
        return this;
    }

    public AcessoBuilder setBancoS16Bank() {
        acesso.setBancoEscolhido("Banco s16 Bank");
        return this;
    }

    public AcessoBuilder setBancoPresil() {
        acesso.setBancoEscolhido("Banco do Presil");
        return this;
    }

    public AcessoBuilder criarAcesso() {
        acesso.setIdEntrada(1);
        acesso.setDataHoraEntrada(LocalDateTime.now().plusHours(-5));
        acesso.setDataHoraSaida(LocalDateTime.now());
        acesso.setFkCliente(setUsuario());
        acesso.setPorcentagemRenda(15);
        acesso.setRenda(140000.0);
        acesso.setTempoFinanciamento(25);
        acesso.setValorImovel(1_200_000);
        acesso.setFkRegiao(setRegiao());
        return this;
    }

    public AcessoBuilder setPaginaSaidaHome() {
        acesso.setPaginaSaida("Pagina Inicial");
        return this;
    }

    public AcessoBuilder setPaginaSaidaCadastro() {
        acesso.setPaginaSaida("Cadastro");
        return this;
    }

    public AcessoBuilder setPaginaSaidaSimulador() {
        acesso.setPaginaSaida("Simulador");
        return this;
    }

    public AcessoBuilder setPaginaSaidaLogin() {
        acesso.setPaginaSaida("Login");
        return this;
    }

    public AcessoBuilder setPaginaSaidaVisualizacao() {
        acesso.setPaginaSaida("Visualização dos dados");
        return this;
    }

    public AcessoBuilder setStatusSaidaConfirmou() {
        acesso.setStatusSaida("Confirmou contratação");
        return this;
    }

    public AcessoBuilder setStatusSaidaNaoConfirmou() {
        acesso.setStatusSaida("Não confirmou");
        return this;
    }

    public Usuario setUsuario() {
        usuario.setId(1);
        usuario.setNome("Teste Legal");
        usuario.setBairro("Interlagos");
        usuario.setEmail("teste.teste@testezinho.com");
        usuario.setSenha("teste123");
        usuario.setCep("01414001");
        usuario.setCnpj("01234567890000");
        usuario.setDataNasc(LocalDate.now().plusYears(-21));
        usuario.setDataCriacao(LocalDateTime.now());
        usuario.setNumero(593);
        return usuario;
    }

    public Regiao setRegiao() {
        regiao.setIdRegiao(1);
        regiao.setDescricaoRegiao("Região de teste legal");
        regiao.setValorRegiao(400000);
        regiao.setDataCraw(LocalDateTime.now());
        return regiao;
    }

    public Acesso getAcesso() {
        return acesso;
    }
}
