package Transformers.Discools.model;

public class DiscosCD {
    private int id;
    private String nome;
    private String artista;
    private String genero;
    private int duracao;
    private float preco;
    private String descricao;
    private int estoque;
    private String tipo;
    private String capa;

    public DiscosCD() {}

    public DiscosCD(int id, String nome, String artista, String genero, int duracao, float preco, 
                    String descricao, int estoque, String tipo, String capa) {
        this.id = id;
        this.nome = nome;
        this.artista = artista;
        this.genero = genero;
        this.duracao = duracao;
        this.preco = preco;
        this.descricao = descricao;
        this.estoque = estoque;
        this.tipo = tipo;
        this.capa = capa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCapa(){
        return capa;
    }

    public void setCapa(String capa){
        this.capa = capa;
    }
}
