package com.bandtec.br.finoban.dominio.listaLigada;

import com.bandtec.br.finoban.dominio.entidades.Admin;
import com.bandtec.br.finoban.dominio.entidades.Usuario;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Locale;

public class HashTable<T> {
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

    public void insere(T objeto) {

        if (objeto instanceof Usuario) {
            Usuario usuario = (Usuario) objeto;
            this.tab[funcaoHash(retornaChar(usuario.getNome()))].insereNode(usuario);
        }

        if (objeto instanceof Admin) {
            Admin admin = (Admin) objeto;
            this.tab[funcaoHash(retornaChar(admin.getEmail()))].insereNode(admin);
        }

    }

    public boolean busca(T objeto) {

        if (objeto instanceof Usuario) {
            Usuario usuario = (Usuario) objeto;
            return this.tab[funcaoHash(retornaChar((usuario.getNome())))]
                    .buscaNodeRecursao(usuario) == null ? false : true;
        }

        if (objeto instanceof Admin) {
            Admin admin = (Admin) objeto;
            return this.tab[funcaoHash(retornaChar((admin.getEmail())))]
                    .buscaNodeRecursao(admin) == null ? false : true;
        }

        throw new NotImplementedException();

    }

    public boolean remove(T objeto) {

        if (objeto instanceof Usuario) {
            Usuario usuario = (Usuario) objeto;
            return this.tab[funcaoHash(retornaChar(usuario.getNome()))].remodeNodeRecursivo(usuario);
        }

        if (objeto instanceof Admin) {
            Admin admin = (Admin) objeto;
            return this.tab[funcaoHash(retornaChar((admin.getEmail())))].remodeNodeRecursivo(admin);
        }

        throw new NotImplementedException();
    }

    public void iniciarListaLigada() {
        for (int i = 0; i < this.tab.length; i++)
            this.tab[i] = new ListaLigada();
    }

}
