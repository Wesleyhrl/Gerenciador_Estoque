package modelo;

import java.util.zip.DataFormatException;

import controle.MainController;

public class Produto implements Comparable<Produto> {
    private String nome;
    private String codigo;
    private int quantidade;
    private String grupo;
    private double valor;
    private String descricao;
    private String data;

    public Produto() {
        this.nome = new String();
        this.codigo = new String();
        this.quantidade = 0;
        this.grupo = new String();
        this.valor = 0;
        this.descricao = new String();
        this.data = new String();
    }

    public Produto(String nome, String codigo, int quantidade, String grupo, double valor, String descricao,
            String data) throws DataFormatException, NumberFormatException, NullPointerException, NomeRepeatException {
        setNome(nome);
        this.codigo = codigo;
        setQuantidade(quantidade);
        this.grupo = grupo;
        setValor(valor);
        this.descricao = descricao;
        setData(data);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) throws NomeRepeatException {
        for (Produto p : MainController.produtos.getProdutos()) {
            if(p.getNome().equals(nome)){
                throw new NomeRepeatException();
            }
        }

        if(nome.equals(null) || nome.equals(" ") || nome.equals("")){
            throw new NullPointerException();
        }
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        NumberFormatException erro = new NumberFormatException();
        
        if(valor < 0 ){
            throw erro;
        }
        this.quantidade = quantidade;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        NumberFormatException erro = new NumberFormatException();
        
        if(valor < 0 ){
            throw erro;
        }
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) throws DataFormatException {
        if(data.equals("00/00/00")){
            this.data = data;
            return;
        }
        int totalCharacters = 0;
        char temp;
        for (int i = 0; i < data.length(); i++) {

            temp = data.charAt(i);
            if (temp == '/')
                totalCharacters++;
        }
        if(totalCharacters < 2){
            throw new  DataFormatException();
        }
        String[] split = data.split("/");
        if(split[0].length() != 2 || split[1].length() != 2 || split[2].length() != 2){
            throw new DataFormatException();
        }
        if(Integer.parseInt(split[2])  < 19 ){
            throw new DataFormatException();
        }if(Integer.parseInt(split[1])  > 12){
            throw new DataFormatException();
        }if(Integer.parseInt(split[0])  > 31){
            throw new DataFormatException();
        }
        this.data = data;
    }

    public int compareTo(Produto o) {
        return nome.compareTo(o.nome);
    }
    
}
