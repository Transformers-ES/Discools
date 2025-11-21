package Transformers.Discools.repository;

import Transformers.Discools.model.DiscosCD;
import Transformers.Discools.model.ItemDoPedido;
import Transformers.Discools.model.Pedido;
import Transformers.Discools.model.Usuario;
    
import java.util.ArrayList;
import java.util.List;

public class Banco {

    // ===================== LISTAS DE DADOS =====================
    private List<Usuario> listaUsuarios;
    private List<DiscosCD> listaDiscos;
    private List<ItemDoPedido> listaItensPedido;
    private List<Pedido> listaPedidos;

    // ===================== CONTADORES DE IDs =====================
    private int idUsuario = 1;
    private int idDisco = 1;
    private int idItemPedido = 1;
    private int idPedido = 1;

    // ===================== CONSTRUTOR =====================
    public Banco() {
        listaUsuarios = new ArrayList<>();
        listaDiscos = new ArrayList<>();
        listaItensPedido = new ArrayList<>();
        listaPedidos = new ArrayList<>();
    }

    // ===================== MÉTODOS DE USUÁRIO =====================
    public void adicionarUsuario(Usuario novoUsuario) {
        novoUsuario.setId(idUsuario++);
        listaUsuarios.add(novoUsuario);
    }

    public List<Usuario> obterTodosUsuarios() {
        return listaUsuarios;
    }

    public Usuario buscarUsuarioPorId(int idUsuario) {
        for (Usuario usuario : listaUsuarios) {
            if (usuario.getId() == idUsuario) {
                return usuario;
            }
        }
        return null; // Retorna null se não encontrar
    }

    public void atualizarUsuario(Usuario usuarioAtualizado) {
        for (int i = 0; i < listaUsuarios.size(); i++) {
            if (listaUsuarios.get(i).getId() == usuarioAtualizado.getId()) {
                listaUsuarios.set(i, usuarioAtualizado);
                return;
            }
        }
    }

    public void removerUsuario(int idUsuario) {
        listaUsuarios.removeIf(usuario -> usuario.getId() == idUsuario);
    }

    // ===================== MÉTODOS DE DISCOSCD =====================
    public void adicionarDisco(DiscosCD novoDisco) {
        novoDisco.setId(idDisco++);
        listaDiscos.add(novoDisco);
    }

    public List<DiscosCD> obterTodosDiscos() {
        return listaDiscos;
    }

    public DiscosCD buscarDiscoPorId(int idDisco) {
        for (DiscosCD disco : listaDiscos) {
            if (disco.getId() == idDisco) {
                return disco;
            }
        }
        return null;
    }

    public void atualizarDisco(DiscosCD discoAtualizado) {
        for (int i = 0; i < listaDiscos.size(); i++) {
            if (listaDiscos.get(i).getId() == discoAtualizado.getId()) {
                listaDiscos.set(i, discoAtualizado);
                return;
            }
        }
    }

    public void removerDisco(int idDisco) {
        listaDiscos.removeIf(disco -> disco.getId() == idDisco);
    }

    // ===================== MÉTODOS DE ITENS DE PEDIDO =====================
    public void adicionarItemPedido(ItemDoPedido novoItem) {
        novoItem.setId(idItemPedido++);
        listaItensPedido.add(novoItem);
    }

    public List<ItemDoPedido> obterTodosItensPedido() {
        return listaItensPedido;
    }

    public ItemDoPedido buscarItemPedidoPorId(int idItem) {
        for (ItemDoPedido item : listaItensPedido) {
            if (item.getId() == idItem) {
                return item;
            }
        }
        return null;
    }

    public void removerItemPedido(int idItem) {
        listaItensPedido.removeIf(item -> item.getId() == idItem);
    }

    // ===================== MÉTODOS DE PEDIDO =====================
    public void adicionarPedido(Pedido novoPedido) {
        novoPedido.setId(idPedido++);
        listaPedidos.add(novoPedido);
    }

    public List<Pedido> obterTodosPedidos() {
        return listaPedidos;
    }

    public Pedido buscarPedidoPorId(int idPedido) {
        for (Pedido pedido : listaPedidos) {
            if (pedido.getId() == idPedido) {
                return pedido;
            }
        }
        return null;
    }

    public void removerPedido(int idPedido) {
        listaPedidos.removeIf(pedido -> pedido.getId() == idPedido);
    }

    // ===================== MÉTODO AUXILIAR PARA RESUMO =====================
    public void mostrarResumoBanco() {
        System.out.println("===== USUÁRIOS =====");
        for (Usuario usuario : listaUsuarios) {
            System.out.println(usuario.getId() + " - " + usuario.getNome() +
                               " | Admin: " + usuario.EhAdministrador());
        }

        System.out.println("\n===== DISCOS =====");
        for (DiscosCD disco : listaDiscos) {
            System.out.println(disco.getId() + " - " + disco.getNome() +
                               " | Preço: R$" + disco.getPreco());
        }

        System.out.println("\n===== PEDIDOS =====");
        for (Pedido pedido : listaPedidos) {
            System.out.println("Pedido " + pedido.getId() + " - Cliente: " +
                               pedido.getUsuario().getNome() +
                               " | Total: R$" + pedido.getTotal());
        }
    }
}
