package modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.zip.DataFormatException;

public class ListaProduto {

    private List<Produto> produtos = new ArrayList<>();

    public void gravar(Produto p) {
        produtos.add(p);
        ordenar();
        try {
            salvarArq("memory.txt");
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
        try {
            salvarArq("memory.txt");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void salvarArq(String nome) throws IOException {
        FileWriter fw = new FileWriter(nome);
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
            buffer.write(Double.toString(produto.getValor()));
            buffer.write(";");
            buffer.write(produto.getDescricao());
            buffer.write(";");
            buffer.write(produto.getData());
            buffer.write("\n");

        }

        buffer.close();
    }

    public void lerArq(String nome) throws IOException, DataFormatException, NomeRepeatException {
        FileReader fr = new FileReader(nome);
        BufferedReader bfr = new BufferedReader(fr);
        String linha = bfr.readLine();
        
        while (linha != null) {
            Produto p = new Produto();
            String[] split = linha.split(";");
            if (split.length > 7 || split.length < 7) {
                bfr.close();
                throw new ArqIncorrectException();
            }
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
