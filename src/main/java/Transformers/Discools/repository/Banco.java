    package Transformers.Discools.repository;

    import Transformers.Discools.model.*;

    import java.util.ArrayList;
    import java.util.List;

    public class Banco {

        // ===================== LISTAS DE DADOS (STATIC) =====================
        private static List<Usuario> listaUsuarios = new ArrayList<>();
        private static List<DiscosCD> listaDiscos = new ArrayList<>();
        private static List<ItemDoPedido> listaItensPedido = new ArrayList<>();
        private static List<Pedido> listaPedidos = new ArrayList<>();
        private static List<CategoriaDisco> listaCategorias = new ArrayList<>();

        // ===================== CONTADORES DE IDs (STATIC) =====================
        private static int idUsuario = 1;
        private static int idDisco = 1;
        private static int idItemPedido = 1;
        private static int idPedido = 1;

        // ===================== CONSTRUTOR PRIVADO (NÃO USADO) =====================
        private Banco() {}

        // ===================== MÉTODOS DE USUÁRIO =====================
        public static void adicionarUsuario(Usuario novoUsuario) {
            novoUsuario.setId(idUsuario++);
            listaUsuarios.add(novoUsuario);
        }

        public static List<Usuario> obterTodosUsuarios() {
            return listaUsuarios;
        }

        public static Usuario buscarUsuarioPorId(int id) {
            return listaUsuarios.stream()
                    .filter(u -> u.getId() == id)
                    .findFirst()
                    .orElse(null);
        }

        public static void atualizarUsuario(Usuario usuarioAtualizado) {
            for (int i = 0; i < listaUsuarios.size(); i++) {
                if (listaUsuarios.get(i).getId() == usuarioAtualizado.getId()) {
                    listaUsuarios.set(i, usuarioAtualizado);
                    return;
                }
            }
        }

        public static void removerUsuario(int id) {
            listaUsuarios.removeIf(u -> u.getId() == id);
        }

        // ===================== MÉTODOS DE DISCO =====================
        public static void adicionarDisco(DiscosCD novoDisco) {
            novoDisco.setId(idDisco++);
            listaDiscos.add(novoDisco);
        }

        public static List<DiscosCD> obterTodosDiscos() {
            return listaDiscos;
        }

        public static DiscosCD buscarDiscoPorId(int id) {
            return listaDiscos.stream()
                    .filter(d -> d.getId() == id)
                    .findFirst()
                    .orElse(null);
        }

        public static void atualizarDisco(DiscosCD discoAtualizado) {
            for (int i = 0; i < listaDiscos.size(); i++) {
                if (listaDiscos.get(i).getId() == discoAtualizado.getId()) {
                    listaDiscos.set(i, discoAtualizado);
                    return;
                }
            }
        }

        public static void removerDisco(int id) {
            listaDiscos.removeIf(d -> d.getId() == id);
        }

        // ===================== MÉTODOS DE ITENS DO PEDIDO =====================
        public static void adicionarItemPedido(ItemDoPedido novoItem) {
            novoItem.setId(idItemPedido++);
            listaItensPedido.add(novoItem);
        }

        public static List<ItemDoPedido> obterTodosItensPedido() {
            return listaItensPedido;
        }

        public static ItemDoPedido buscarItemPedidoPorId(int id) {
            return listaItensPedido.stream()
                    .filter(i -> i.getId() == id)
                    .findFirst()
                    .orElse(null);
        }

        public static void removerItemPedido(int id) {
            listaItensPedido.removeIf(i -> i.getId() == id);
        }

        // ===================== MÉTODOS DE PEDIDO =====================
        public static void adicionarPedido(Pedido novoPedido) {
            novoPedido.setId(idPedido++);
            listaPedidos.add(novoPedido);
        }

        public static List<Pedido> obterTodosPedidos() {
            return listaPedidos;
        }

        public static Pedido buscarPedidoPorId(int id) {
            return listaPedidos.stream()
                    .filter(p -> p.getId() == id)
                    .findFirst()
                    .orElse(null);
        }

        public static void removerPedido(int id) {
            listaPedidos.removeIf(p -> p.getId() == id);
        }

        // ===================== MÉTODOS DE CATEGORIA =====================
        public static void adicionarCategoria(CategoriaDisco novaCategoria) {
            listaCategorias.add(novaCategoria);
        }

        public static List<CategoriaDisco> obterTodasCategorias() {
            return listaCategorias;
        }

        public static CategoriaDisco buscarCategoriaPorId(int id){
            return listaCategorias.stream()
                    .filter(c -> c.getId() == id)
                    .findFirst()
                    .orElse(null);
        }

        public static CategoriaDisco buscarCategoriaPorNome(String nome) {
            return listaCategorias.stream()
                    .filter(c -> c.getNomeCategoriaDisco().equals(nome))
                    .findFirst()
                    .orElse(null);
        }

        public static void removerCategoria(String nome) {
            listaCategorias.removeIf(c -> c.getNomeCategoriaDisco().equals(nome));
        }
    }
