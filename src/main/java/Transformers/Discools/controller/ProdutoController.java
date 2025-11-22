package Transformers.Discools.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class ProdutoController {

    // Renderiza a página PRODUTO.HTML e envia o ID do produto para o frontend
    @GetMapping("produto/{id}")
    public String paginaProduto(@PathVariable int id, Model model) {
        model.addAttribute("produtoId", id);
        return "produto";
 
    }
}
