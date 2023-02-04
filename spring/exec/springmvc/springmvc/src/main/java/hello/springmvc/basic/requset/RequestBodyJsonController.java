package hello.springmvc.basic.requset;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.springmvc.basic.HelloData;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyJsonController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/request-bodt-json-v1")
    public void requestBodyJsonV1(HttpServletRequest req, HttpServletResponse res) throws IOException {
        ServletInputStream inputStream = req.getInputStream();
        String s = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info(s);
        HelloData helloData = objectMapper.readValue(s, HelloData.class);
        log.info(helloData.toString());
        res.getWriter().write("ok");

    }

    @ResponseBody
    @PostMapping("/request-bodt-json-v2")
    public String requestBodyJsonV2(@RequestBody String messagebody) throws IOException {

        log.info(messagebody);
        HelloData helloData = objectMapper.readValue(messagebody, HelloData.class);
        log.info(helloData.toString());
        return "ok";

    }

    @ResponseBody
    @PostMapping("/request-bodt-json-v3")
    public String requestBodyJsonV3(@RequestBody HelloData helloData) throws IOException {

        log.info(helloData.toString());
        return "ok";

    }

    @ResponseBody
    @PostMapping("/request-bodt-json-v4")
    public String requestBodyJsonV4(HttpEntity<HelloData> helloData) throws IOException {

        log.info(helloData.getBody().toString());
        return "ok";

    }

    @ResponseBody
    @PostMapping("/request-bodt-json-v5")
    public HelloData requestBodyJsonV5(@RequestBody HelloData helloData) throws IOException {

        log.info(helloData.toString());
        return helloData;

    }

}
