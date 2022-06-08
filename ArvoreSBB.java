public class ArvoreSBB {
    private static class No {
        Item reg ; No esq, dir ; byte incE , incD;
    }
    private static final byte Horizontal = 0;
    private static final byte Vertical = 1;
    private No raiz;
    private boolean propSBB;
    // Entram aqui os métodos privados das transparências 21, 40, 41 e 48
    public ArvoreSBB ( ) {
        this.raiz = null;
        this.propSBB = true;
    }
    public Item pesquisa(Item reg) {
        return this.pesquisa(reg, this.raiz) ; 
    }
    public void insere(Item reg) {
        this.raiz = insere(reg, null, this.raiz, true); 
    }
    /*Realiza, recursivamente, a pesquisa. Recebe como parâmetro um Item e um nó.
    retorna um Item (caso encontrado) ou null (caso não encontrado);*/
    private Item pesquisa(Item reg , No p) { 
        if (p == null) {
            return null; // Registro não encontrado
        // Compara o item de interesse no item presente no Nó p.
        }else if (reg.compara(p.reg) < 0){ 
            //Caso o Item a ser procurado for menor que o Item presente no Nó, a pesquisa é chamada novamente
            //passando como parâmetro o nó da esquerda (valor menor que seu nó pai).
            reg.incrementaComparacoes();
            return pesquisa(reg, p.esq);
        }else if (reg.compara(p.reg) > 0){
            //Caso o Item a ser procurado for maior que o Item presente no Nó, a pesquisa é chamada novamente
            //passando como parâmetro o nó da direita (valor maior que seu nó pai).
            reg.incrementaComparacoes();
            return pesquisa(reg, p.dir);
        }else{ 
            //Caso contrário, o item passado como parâmetro é igual ao item presente no Nó, ou seja, foi encontrado.
            reg.incrementaComparacoes();
            return p.reg;
        }
    }
    private No insere(Item reg, No pai, No filho, boolean filhoEsq) {
        if (filho == null) { //Caso o No filho seja nulo, seus valores são inicializados.
            filho = new No () ; 
            filho.reg = reg;
            filho.incE = Vertical ; 
            filho.incD = Vertical ;
            filho.esq = null ; 
            filho.dir = null ;
            if (pai != null)
                if (filhoEsq){ 
                    pai.incE = Horizontal ; 
                }else{ 
                    pai.incD = Horizontal;
                }
                this.propSBB = false;
        }else if (reg.compara (filho.reg) < 0) {
            filho.esq = insere (reg, filho, filho.esq, true);
            if (!this.propSBB){
                if (filho.incE == Horizontal) {
                    if (filho.esq.incE == Horizontal) {
                        filho = this.ee(filho) ; // realiza a rotação esquerda-esquerda
                        if (pai != null){
                            if (filhoEsq){ 
                                pai.incE=Horizontal; 
                            }else{ 
                                pai.incD=Horizontal;
                            }
                        }
                    }else if (filho.esq.incD == Horizontal) {
                        filho = this.ed(filho) ; // realiza a rotação esquerda-direita
                        if (pai != null){
                            if (filhoEsq){ 
                                pai.incE=Horizontal;
                            }else{ 
                                pai.incD=Horizontal;
                            }
                        }
                    }
                }
            }else{ 
                this.propSBB = true;
            }
        }else if (reg.compara (filho.reg) > 0) {
            filho.dir = insere (reg, filho, filho.dir, false);
            if (!this.propSBB){
                if (filho.incD == Horizontal) {
                    if (filho.dir.incD == Horizontal) {
                        filho = this.dd(filho); // realiza a rotação direita-direita
                        if (pai != null){
                            if ( filhoEsq ){ 
                                pai.incE=Horizontal;
                            }else{ 
                                pai.incD=Horizontal;
                            }
                        }
                    }else if (filho.dir.incE == Horizontal) {
                        filho = this.de(filho); // realiza a rotação direita-esquerda
                        if (pai != null){
                            if (filhoEsq){
                                pai.incE=Horizontal;
                            }else{
                                pai.incD=Horizontal;
                            }
                        }
                    }
                }
            }else{ 
                this.propSBB = true;
            }
        }else{
            this.propSBB = true;
        }
        return filho;
    }
    

    private No ee (No ap) {
        No ap1 = ap.esq; 
        ap.esq = ap1. dir; 
        ap1. dir = ap;
        ap1.incE = Vertical; 
        ap.incE = Vertical; 
        ap = ap1;
        return ap;
    }
    private No ed (No ap) {
        No ap1 = ap.esq; 
        No ap2 = ap1.dir; 
        ap1.incD = Vertical;
        ap.incE = Vertical; 
        ap1.dir = ap2.esq; 
        ap2.esq = ap1;
        ap.esq = ap2. dir; 
        ap2.dir = ap; 
        ap = ap2;
        return ap;
    }
    private No dd (No ap) {
        No ap1 = ap. dir; 
        ap.dir = ap1.esq; 
        ap1.esq = ap;
        ap1.incD = Vertical; 
        ap.incD = Vertical; 
        ap = ap1;
        return ap;
    }
    private No de (No ap) {
        No ap1 = ap.dir; 
        No ap2 = ap1.esq; 
        ap1.incE = Vertical;
        ap.incD = Vertical;  
        ap1.esq = ap2. dir; 
        ap2. dir = ap1;
        ap. dir = ap2.esq; 
        ap2.esq = ap; 
        ap = ap2;
        return ap;
    }
}
