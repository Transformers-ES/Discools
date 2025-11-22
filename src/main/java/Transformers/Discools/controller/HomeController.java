package Transformers.Discools.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // Renderiza a página HOME.HTML
    @GetMapping("/")
    public String index() {
        return "home"; 
    }
}
