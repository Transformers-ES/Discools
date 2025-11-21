package Transformers.Discools.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "home"; 
    }

    @GetMapping("/pagamento")
    public String pagamento(){
        return "pagamento";
    }
    
    @GetMapping("/prod")
    public String produtos(){
        return "prod";
    }
}
