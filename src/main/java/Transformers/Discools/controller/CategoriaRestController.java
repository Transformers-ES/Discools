package Transformers.Discools.controller;

import Transformers.Discools.model.CategoriaDisco;
import Transformers.Discools.service.DiscoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//API REST QUE RETORNA OS DADOS DAS CATEGORIAS

@RestController
@RequestMapping("/api/categorias")
public class CategoriaRestController {

    @Autowired
    private DiscoService discoService;

    // REGRA DE NEGÓCIO 1: Pega uma categoria (em alta, lançamentos, recomendados) e retorna os discos dela
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDisco> listarPorCategoria(@PathVariable int id) {
        CategoriaDisco categoria = discoService.listarPorCategoria(id);

        if (categoria == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(categoria);
    }
}
