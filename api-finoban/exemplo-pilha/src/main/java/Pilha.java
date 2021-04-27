public class Pilha {

        int topo;
        int pilha[];

        public Pilha(int capacidade) {
            this.pilha = new int[capacidade];
            this.topo = -1;
        }

        public boolean isEmpty(){
            if (topo == -1){
                return true;
            }else {
                return false;
            }
        }

    public boolean isFull(){
        return this.pilha.length - 1 == this.topo;
    }

    public void push(int valor){
        if (isFull()){
            System.out.println("Pilha já está cheia!");
        }else {
            this.pilha[this.topo] = valor;
            this.topo++;
        }
    }



}
