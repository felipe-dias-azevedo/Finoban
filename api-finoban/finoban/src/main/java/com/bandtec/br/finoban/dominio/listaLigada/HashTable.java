package com.bandtec.br.finoban.dominio.listaLigada;

import com.bandtec.br.finoban.dominio.entidades.Usuario;

import java.util.Locale;

public class HashTable {
    private ListaLigada[] tab;

    public HashTable(int tamanho) {
        this.tab = new ListaLigada[tamanho];
        iniciarListaLigada();
    }

    public int funcaoHash(Character caractere) {
        return caractere % 97;
    }
    
    public char retornaChar(String nome) {
        return nome.toLowerCase(Locale.ROOT).charAt(0);
    }

    public void insere(Usuario usuario) {
        this.tab[funcaoHash(retornaChar(usuario.getNome()))].insereNode(usuario);
    }

    public boolean busca(Usuario usuario) {
        return this.tab[funcaoHash(retornaChar((usuario.getNome())))]
                .buscaNodeRecursao(usuario) == null ? false : true;
    }

    public boolean remove(Usuario usuario) {
        return this.tab[funcaoHash(retornaChar(usuario.getNome()))].remodeNodeRecursivo(usuario);
    }

    public void iniciarListaLigada() {
        for (int i = 0; i < this.tab.length; i++)
            this.tab[i] = new ListaLigada();
    }

}
