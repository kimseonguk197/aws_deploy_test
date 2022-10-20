package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    //8080만 치고 들어왔을경우 아래 호출
    @GetMapping("/")
    public String home(){
        return "home";
    }
}
