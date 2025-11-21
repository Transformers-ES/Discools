package Transformers.Discools.model;

public class ItemDoPedido {
    private int id;
    private DiscosCD disco;
    private int quantidade;

    public ItemDoPedido() {}

    public ItemDoPedido(int id, DiscosCD disco, int quantidade) {
        this.id = id;
        this.disco = disco;
        this.quantidade = quantidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DiscosCD getDisco() {
        return disco;
    }

    public void setDisco(DiscosCD disco) {
        this.disco = disco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public float getSubtotal() {
        if (disco != null) {
            return disco.getPreco() * quantidade;
        }
        return 0;
    }
}
