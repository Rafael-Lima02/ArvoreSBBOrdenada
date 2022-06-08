class Teste {
    public static void main(String[] args) {
        ArvoreSBB arv = new ArvoreSBB();
        Item item = new Item(0);
        Item itemP = new Item(10150);

        for(int i = 10000; i <= 100000; i++){
            item = new Item(i);
            arv.insere(item);
          }
        long start = System.nanoTime();
        item = arv.pesquisa(itemP);
        System.out.println("Numero de comparacoes:   "+itemP.getComparacoes());
        long delay = System.nanoTime() - start;
        System.out.println("Demorou " + delay + " nanosegundos");
    }
}
