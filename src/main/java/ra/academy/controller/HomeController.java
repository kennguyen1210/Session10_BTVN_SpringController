package ra.academy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {
    @GetMapping("/user")
    public String categoryRender(){
        return "user";
    }
    @GetMapping("/index")
    public String indexRender(){
        return "index1";
    }
}
