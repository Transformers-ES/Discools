package Transformers.Discools.controller;

import Transformers.Discools.model.DiscosCD;
import Transformers.Discools.service.DiscoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//API REST QUE RETORNA OS DADOS DOS DISCOS

@RestController
@RequestMapping("/api/discos")
public class DiscoRestController {

    @Autowired
    private DiscoService discoService;
    
    // REGRA DE NEGÓCIO 2: Pega um disco específico e retorna suas informações
    @GetMapping("/{id}")
    public DiscosCD buscarPorId(@PathVariable int id) {
        return discoService.buscarPorId(id);
    }
}
