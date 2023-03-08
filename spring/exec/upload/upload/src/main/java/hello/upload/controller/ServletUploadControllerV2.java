package hello.upload.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Collection;

@Controller
@Slf4j
@RequestMapping("/servlet/v2")
public class ServletUploadControllerV2 {

    @Value("${file.dir}")
    private String fileDir;

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

        for (Part part : parts) {
            log.info("---PART---");
            log.info("name={}", part.getName());
            Collection<String> headerNames = part.getHeaderNames();
            for (String headerName : headerNames) {
                log.info("header{}:{}", headerName, part.getHeader(headerName));
            }
            log.info("submitterFIlename={}", part.getSubmittedFileName());
            log.info("size={}", part.getSize());

            InputStream inputStream = part.getInputStream();
            String body = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
            // log.info("body={}", body);

            if (StringUtils.hasText(part.getSubmittedFileName())) {
                String fullpath = fileDir + part.getSubmittedFileName();
                part.write(fullpath);
            }
        }

        return "upload-form";


    }


}
