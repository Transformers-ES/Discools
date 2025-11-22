package Transformers.Discools.service;

import org.springframework.stereotype.Service;
import Transformers.Discools.repository.Banco;
import Transformers.Discools.model.CategoriaDisco;
import Transformers.Discools.model.DiscosCD;
import java.util.List;

@Service
public class DiscoService {

    /*
    public List<DiscosCD> listarTodos() {
        return Banco.obterTodosDiscos();
    }

    public DiscosCD buscarPorId(int id) {
        return Banco.buscarDiscoPorId(id);
    }
    */
    
    // REGRA DE NEGÓCIO 1: Pega uma categoria (em alta, lançamentos, recomendados) e retorna os discos dela
    public CategoriaDisco listarPorCategoria(int categoriaId) {
        return Banco.buscarCategoriaPorId(categoriaId);
    }

    
}
