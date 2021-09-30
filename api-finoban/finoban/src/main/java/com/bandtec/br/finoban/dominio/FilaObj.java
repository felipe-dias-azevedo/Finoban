package com.bandtec.br.finoban.dominio;

public class FilaObj <T>{

    private int tamanho;
    private T[] fila;

    public FilaObj(int capacidade) {
        this.tamanho = 0;
        this.fila = (T[]) new Object[capacidade];
    }

    public Boolean isEmpty() {
        return this.tamanho == 0;
    }

    public Boolean isFull() {
        return this.tamanho == this.fila.length;
    }

    public void insert(T item) {
        if (!isFull()) {
            this.fila[tamanho++] = item;
        } else {
            System.out.println("A fila já está cheia");
        }
    }

    public T peek() {
        return this.fila[0];
    }

    public T poll() {
        T item = peek();

        if (!isEmpty()) {
            for (int i = 0; i < this.tamanho - 1; i++) {
                this.fila[i] = this.fila[i + 1];
            }

            fila[--tamanho] = null;

        }

        return item;
    }

    public void exibe() {
        if (isEmpty()) {
            System.out.println("Fila Vazia");
        } else {
            for (int i = 0; i < tamanho; i++) {
                System.out.println(this.fila[i]);
            }
        }

    }

}
