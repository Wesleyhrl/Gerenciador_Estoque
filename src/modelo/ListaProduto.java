package modelo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ListaProduto {

    private  List<Produto> produtos = new ArrayList<>();
    


    public void gravar(Produto p){
        produtos.add(p);
        ordenar();
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public Produto buscar(String nome){
        return null;
    }
    public void ordenar(){
       Collections.sort(produtos);
    }
    public void remover (Produto antigo){
        produtos.remove(antigo);
        ordenar();

    }
    

    



}
