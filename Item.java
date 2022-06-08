public class Item {
    private int chave;
    private int comparacoes;
    public Item(int chave) {
      this.chave = chave;
      this.comparacoes = 0;
    }
    public int compara(Item it) {
      Item item = it;
      if (this.chave < item.chave)
        return -1;
      else if (this.chave > item.chave)
        return 1;
    return 0;
  }
    public int getChave() {
      return chave;
    }
    public int getComparacoes(){
      return comparacoes;
    }
    public void incrementaComparacoes(){
      this.comparacoes++;
    }
  
  }