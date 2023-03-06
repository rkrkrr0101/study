package hello.typeconverter.controller;

import hello.typeconverter.type.IpPort;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello-v1")
    public String helloV1(HttpServletRequest req){
        String data = req.getParameter("data");
        Integer intValue = Integer.valueOf(data);
        System.out.println("intValue = " + intValue);
        return "ok";
    }
    @GetMapping("/hello-v2")
    public String helloV2(@RequestParam Integer data){

        Integer intValue = Integer.valueOf(data);
        System.out.println("intValue = " + intValue);
        return "ok";
    }
    @GetMapping("/hello-v3")
    public String helloV3(@RequestParam IpPort data){

        Integer intValue = data.getPort();
        System.out.println("intValue = " + intValue);
        return "ok";
    }
}
