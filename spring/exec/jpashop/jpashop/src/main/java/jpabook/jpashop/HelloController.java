package jpabook.jpashop;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller

public class HelloController {


    @GetMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("data", "hellooooo");
        return "hello";
    }


}
