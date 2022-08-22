package modelo;

import java.util.ArrayList;
import java.util.List;

public class ListaProduto {

    private  List<Produto> produtos = new ArrayList<>();
    


    public void gravar(Produto p){
        produtos.add(p);
        
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public Produto buscar(String nome){
        return null;
    }
    public List<Produto> ordenar(){
        return null;
    }
    public void editar (Produto antigo){
        produtos.remove(antigo);

    }
    

    



}
