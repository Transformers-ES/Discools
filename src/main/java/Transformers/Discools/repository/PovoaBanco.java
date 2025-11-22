package Transformers.Discools.repository;

import Transformers.Discools.model.DiscosCD;
import Transformers.Discools.model.Usuario;
import Transformers.Discools.model.CategoriaDisco;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;

import java.util.ArrayList;
import java.util.List;

@Component
public class PovoaBanco {

    @PostConstruct
    public void init() {
        // Proteção contra duplicatas em reloads do devtools
        if (Banco.obterTodasCategorias().isEmpty()) {
            carregarDadosIniciais();
        }
    }

    // ===================== DADOS INICIAIS =====================
    private void carregarDadosIniciais() {

        // Criação de todos os produtos do sistema
        DiscosCD disco1 = new DiscosCD(1, "this is what ____ feels like", "JVKE", "Pop", 42, 59.90f, "Um dos álbuns mais lindos da história.", 10, "CD", "/midia/capas/capa1.jpg");
        Banco.adicionarDisco(disco1);

        DiscosCD disco2 = new DiscosCD(2, "Thriller", "Michael Jackson", "Pop", 42, 79.90f, "O disco mais vendido de todos os tempos, um clássico absoluto.", 12, "Vinil", "/midia/capas/capa1.jpg");
        Banco.adicionarDisco(disco2);

        DiscosCD disco3 = new DiscosCD(3, "Back in Black", "AC/DC", "Rock", 41, 69.90f, "Um dos maiores álbuns de rock da história, cheio de energia.", 8, "CD", "/midia/capas/capa1.jpg");
        Banco.adicionarDisco(disco3);

        DiscosCD disco4 = new DiscosCD(4, "Nevermind", "Nirvana", "Grunge", 49, 72.50f, "O álbum que marcou uma geração e mudou o rock para sempre.", 7, "Vinil", "/midia/capas/capa1.jpg");
        Banco.adicionarDisco(disco4);

        DiscosCD disco5 = new DiscosCD(5, "Abbey Road", "The Beatles", "Rock", 47, 84.90f, "Clássico atemporal dos Beatles, com produção impecável.", 9, "Vinil", "/midia/capas/capa1.jpg");
        Banco.adicionarDisco(disco5);

        DiscosCD disco6 = new DiscosCD(6, "Rumours", "Fleetwood Mac", "Rock", 39, 74.90f, "Um dos discos mais icônicos da história, cheio de harmonias perfeitas.", 11, "CD", "/midia/capas/capa1.jpg");
        Banco.adicionarDisco(disco6);

        DiscosCD disco7 = new DiscosCD(7, "Hotel California", "Eagles", "Rock", 43, 69.90f, "Um marco do rock clássico, com guitarras marcantes e identidade única.", 10, "CD", "/midia/capas/capa1.jpg");
        Banco.adicionarDisco(disco7);

        DiscosCD disco8 = new DiscosCD(8, "The Dark Side of the Moon", "Pink Floyd", "Progressive Rock", 42, 89.90f, "Obra-prima conceitual com som inovador e atmosfera única.", 5, "Vinil", "/midia/capas/capa1.jpg");
        Banco.adicionarDisco(disco8);

        DiscosCD disco9 = new DiscosCD(9, "Born to Die", "Lana Del Rey", "Indie Pop", 50, 69.90f, "Álbum cinematográfico com estética marcante e melancólica.", 13, "CD", "/midia/capas/capa1.jpg");
        Banco.adicionarDisco(disco9);

        DiscosCD disco10 = new DiscosCD(10, "21", "Adele", "Soul/Pop", 48, 75.90f, "Um dos discos mais premiados da década, com vocais poderosos.", 11, "CD", "/midia/capas/capa1.jpg");
        Banco.adicionarDisco(disco10);

        DiscosCD disco11 = new DiscosCD(11, "Come Away With Me", "Norah Jones", "Jazz", 40, 64.90f, "Um álbum suave, elegante e premiado com vários Grammys.", 8, "CD", "/midia/capas/capa1.jpg");
        Banco.adicionarDisco(disco11);

        DiscosCD disco12 = new DiscosCD(12, "Legend", "Bob Marley & The Wailers", "Reggae", 51, 72.90f, "A coletânea definitiva de Bob Marley, atemporal e cheia de vida.", 12, "Vinil", "/midia/capas/capa1.jpg");
        Banco.adicionarDisco(disco12);


        // ========== CATEGORIAS DA HOME ==========
        Banco.adicionarCategoria(new CategoriaDisco(1, "Em alta", new ArrayList<>(List.of(disco1, disco2, disco3, disco4))));
        Banco.adicionarCategoria(new CategoriaDisco(2, "Lançamentos", new ArrayList<>(List.of(disco5, disco6, disco7, disco8))));
        Banco.adicionarCategoria(new CategoriaDisco(3, "Recomendados", new ArrayList<>(List.of(disco9, disco10, disco11, disco12))));
    }

}
