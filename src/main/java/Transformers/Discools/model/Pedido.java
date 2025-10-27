package Transformers.Discools.model;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private int id;
    private Usuario usuario;
    private List<ItemDoPedido> itens;

    public Pedido() {
        this.itens = new ArrayList<>();
    }

    public Pedido(int id, Usuario usuario, List<ItemDoPedido> itens) {
        this.id = id;
        this.usuario = usuario;
        this.itens = itens;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<ItemDoPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemDoPedido> itens) {
        this.itens = itens;
    }

    public float getTotal() {
        float total = 0;
        for (ItemDoPedido item : itens) {
            total += item.getSubtotal();
        }
        return total;
    }

    public void adicionarItem(ItemDoPedido item) {
        itens.add(item);
    }

    public void removerItem(ItemDoPedido item) {
        itens.remove(item);
    }
}
