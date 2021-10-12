package com.bandtec.br.finoban.dominio.listaLigada;

import com.bandtec.br.finoban.dominio.entidades.Admin;
import com.bandtec.br.finoban.dominio.entidades.Usuario;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class ListaLigada<T> {
    private Node<T> head;

    public ListaLigada() {
        this.head = new Node(0);
    }

    public void insereNode(T valor) {
        Node node = new Node(valor);
        node.setNext(head.getNext());
        head.setNext(node);
    }

    public void exibe() {
        Node atual = head.getNext();
        while (atual != null) {
            System.out.println(atual.getInfo());
            atual = atual.getNext();
        }
    }

    public Node buscaNode(T valor) {
        Node atual = head.getNext();
        while (atual != null) {
            if (atual.getInfo() == valor)
                return atual;

            atual = atual.getNext();
        }
        return null;
    }

    public boolean removeNode(T valor) {
        Node ant = head;
        Node atual = head.getNext();
        while (atual != null) {
            if (atual.getInfo() == valor) {
                ant.setNext(atual.getNext());
                return true;
            }
            else {
                ant = atual;
                atual = atual.getNext();
            }
        }
        return false;
    }

    public int getTamanho() {
        Node atual = head.getNext();
        int tam = 0;
        while (atual != null) {
            tam += 1;
            atual = atual.getNext();
        }
        return tam;
    }

    public void concatena(ListaLigada listaLigada) {
        Node atual = listaLigada.getHead().getNext();
        while (atual != null) {
            insereNode((T)atual.getInfo());
            atual = atual.getNext();
        }
    }

    public boolean compara(ListaLigada listaLigada) {

        if (getTamanho() < listaLigada.getTamanho())
            return false;

        Node listaAtual = head.getNext();
        Node listaNova = listaLigada.getHead().getNext();

        while (listaAtual != null) {
            if (listaAtual.getInfo() != listaNova.getInfo())
                return false;

            listaAtual = listaAtual.getNext();
            listaNova = listaNova.getNext();
        }
        return true;
    }

    public Node getHead() {
        return head;
    }

    public void inverte(){

        Node prev = null;
        Node next = null;
        Node current = getHead();
        while (current != null) {
            next = current.getNext();
            current.setNext(prev);
            prev = current;
            current = next;
        }
        head = prev;
    }

    public void exibeRecursao() {
        Node atual = head.getNext();
        exibeRecursao(atual);
    }
    public void exibeRecursao(Node node) {
        if (node != null) {
            System.out.println(node.getInfo());
            exibeRecursao(node.getNext());
        }
    }

    public Node buscaNodeRecursao(T valor) {
        Node atual = head.getNext();

        if (valor instanceof Usuario) {
            Usuario usuario = (Usuario) valor;
            return buscaNodeRecursaoUsuario(atual, usuario);
        }

        if (valor instanceof Admin) {
            Admin admin = (Admin) valor;
            buscaNodeRecursaoAdmin(atual, admin);
        }

        return atual;
    }

    private Node buscaNodeRecursaoUsuario(Node node, Usuario usuario) {
        if (node != null) {
            Usuario usuarioNode = (Usuario) node.getInfo();
            if (usuario.getEmail().equals(usuarioNode.getEmail())) {
                return node;
            }
        } else {
            return null;
        }
        return buscaNodeRecursaoUsuario(node.getNext(), usuario);
    }

    private Node buscaNodeRecursaoAdmin(Node node, Admin admin) {
        if (node != null) {
            Admin adminNode = (Admin) node.getInfo();
            if (admin.getEmail().equals(adminNode.getEmail())) {
                return node;
            }
        } else {
            return null;
        }
        return buscaNodeRecursaoAdmin(node.getNext(), admin);
    }

    public boolean remodeNodeRecursivo(T valor) {
        Node ant = head;
        Node atual = head.getNext();

        if (valor instanceof Usuario)
            return removeNodeRecursivoUsuario(atual, ant, valor);

        if (valor instanceof Admin)
            return removeNodeRecursivoAdmin(atual, ant, valor);

        return false;
    }

    private boolean removeNodeRecursivoUsuario(Node atual, Node ant, T valor) {
        if (atual != null) {
            Usuario usuario = (Usuario) atual.getInfo();
            if (usuario.getEmail().equals(((Usuario) valor).getEmail())) {
                ant.setNext(atual.getNext());
                return true;
            }
        } else {
            return false;
        }
        return removeNodeRecursivoUsuario(atual.getNext(), atual, valor);
    }

    private boolean removeNodeRecursivoAdmin(Node atual, Node ant, T valor) {
        if (atual != null) {
            Admin usuario = (Admin) atual.getInfo();
            if (usuario.getEmail().equals(((Admin) valor).getEmail())) {
                ant.setNext(atual.getNext());
                return true;
            }
        } else {
            return false;
        }
        return removeNodeRecursivoAdmin(atual.getNext(), atual, valor);
    }

    public int getTamanhoRecursivo() {
        Node atual = head.getNext();
        return getTamanhoRecursivo(atual);
    }

    private int getTamanhoRecursivo(Node atual) {
        if (atual != null) {
            return 1 + getTamanhoRecursivo(atual.getNext());
        }
        return 0;
    }
}
