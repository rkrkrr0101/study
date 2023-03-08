package hello.upload.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.Collection;

@Controller
@Slf4j
@RequestMapping("/servlet/v1")
public class ServletUploadControllerV1 {

    @GetMapping("/upload")
    public String newFile() {
        return "upload-form";
    }

    @PostMapping("/upload")
    public String saveFileV1(HttpServletRequest req) throws ServletException, IOException {
        log.info("req = {}", req);
        String itemName = req.getParameter("itemName");
        log.info("itemName={}", itemName);
        System.out.println("itemName = " + itemName);
        Collection<Part> parts = req.getParts();
        log.info("part={}", parts);
        return "upload-form";


    }


}
