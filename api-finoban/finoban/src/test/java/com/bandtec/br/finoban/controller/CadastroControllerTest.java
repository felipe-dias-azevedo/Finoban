package com.bandtec.br.finoban.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CadastroControllerTest {

    @Test
    void novoCadastro() {

        Mockito.when(repository.findAll()).thenReturn(new ArrayList<>());

        ResponseEntity<List<Imposto>> resposta = controller.getImpostos();

        assertEquals(204, resposta.getStatusCodeValue());
        assertNull(resposta.getBody()); // verifica se resposta.getBody()
    }

    @Test
    void listarUsuarios() {
    }

    @Test
    void getUsuario() {
    }

    @Test
    void novaAvaliacao() {
    }

    @Test
    void getAvaliacoes() {
    }

    @Test
    void getAvaliacao() {
    }

    @Test
    void deleteAvaliacao() {
    }

    @Test
    void deleteUsuario() {
    }
}