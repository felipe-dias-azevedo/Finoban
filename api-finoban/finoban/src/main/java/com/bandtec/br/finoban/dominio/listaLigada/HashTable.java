package com.bandtec.br.finoban.dominio.listaLigada;

import com.bandtec.br.finoban.dominio.entidades.Usuario;

public class HashTable {
    private ListaLigada[] tab;

    public HashTable(int tamanho) {
        this.tab = new ListaLigada[tamanho];
        iniciarListaLigada();
    }

    public int funcaoHash(int x) {
        return x % this.tab.length;
    }

    public void insere(Usuario usuario) {
        this.tab[funcaoHash(usuario.getId())].insereNode(usuario);
    }

    public boolean busca(Usuario usuario) {
        return this.tab[funcaoHash(usuario.getId())].buscaNodeRecursao(usuario) == null ? false : true;
    }

    public boolean remove(Usuario usuario) {
        return this.tab[funcaoHash(usuario.getId())].remodeNodeRecursivo(usuario);
    }

    public void iniciarListaLigada() {
        for (int i = 0; i < this.tab.length; i++)
            this.tab[i] = new ListaLigada();
    }
}
