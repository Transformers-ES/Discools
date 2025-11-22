package Transformers.Discools.controller;

import Transformers.Discools.model.CategoriaDisco;
import Transformers.Discools.service.DiscoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private DiscoService discoService;

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDisco> listarPorCategoria(@PathVariable int id) {
        CategoriaDisco categoria = discoService.listarPorCategoria(id);

        if (categoria == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(categoria);
    }
}
