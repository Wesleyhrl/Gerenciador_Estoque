package modelo;

public class Produto {
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
            String data) {
        this.nome = nome;
        this.codigo = codigo;
        this.quantidade = quantidade;
        this.grupo = grupo;
        this.valor = valor;
        this.descricao = descricao;
        this.data = data;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
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

    public void setData(String data) {
        this.data = data;
    }

}
