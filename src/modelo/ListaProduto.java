package modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.zip.DataFormatException;

public class ListaProduto {

    private List<Produto> produtos = new ArrayList<>();

    public void gravar(Produto p) {
        produtos.add(p);
        ordenar();
        try {
            salvarArq();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void ordenar() {
        Collections.sort(produtos);
    }

    public void remover(Produto antigo) {
        produtos.remove(antigo);
        ordenar();

    }

    public void salvarArq() throws IOException {
        FileWriter fw = new FileWriter("memory.txt");  
        BufferedWriter buffer = new BufferedWriter(fw);
		for (Produto produto : produtos) {
            buffer.write(produto.getNome());
            buffer.write(";");
            buffer.write(produto.getCodigo());
            buffer.write(";");
            buffer.write(Integer.toString(produto.getQuantidade()));
            buffer.write(";");
            buffer.write(produto.getGrupo());
            buffer.write(";");
            buffer.write( Double.toString(produto.getValor()) );
            buffer.write(";");
            buffer.write(produto.getDescricao());
            buffer.write(";");
            buffer.write(produto.getData());
            buffer.write("\n");
            
        }
        
        buffer.close();
    }
    public void lerArq() throws IOException, DataFormatException{
        FileReader fr = new FileReader("memory.txt");
        BufferedReader bfr = new BufferedReader(fr);
        String linha = bfr.readLine();
               
        while(linha != null) {
            Produto p = new Produto(); 
            String[] split = linha.split(";");
            linha = bfr.readLine();
            p.setNome(split[0]);
            p.setCodigo(split[1]);
            p.setQuantidade(Integer.parseInt(split[2]));
            p.setGrupo(split[3]);
            p.setValor(Double.parseDouble(split[4]));
            p.setDescricao(split[5]);
            p.setData(split[6]);
            
         produtos.add(p);   
        }
        bfr.close();

    }

}
