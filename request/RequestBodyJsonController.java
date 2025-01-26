package hello.springmvc.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.springmvc.basic.HelloData;
import jakarta.servlet.ServletException;
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

    @PostMapping("/request-body-json-v1")
    public void requestBodyJsonV1(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody = {}", messageBody);
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("username = {}, age = {}",helloData.getUsername(), helloData.getAge());

        response.getWriter().write("ok");
    }

    @ResponseBody
    @PostMapping("/request-body-json-v2")
    public String requestBodyJsonV2(@RequestBody String messageBody)
            throws IOException, ServletException {
        log.info("messageBody = {}", messageBody);
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("username = {}, age = {}",helloData.getUsername(), helloData.getAge());

        return "ok";
    }

    @ResponseBody
    @PostMapping("/request-body-json-v3")
    // @RequestBody 생략 시 ModelAttribute로 읽어 요청 파라미터(form, queryString)을 읽게 된다.
    public String requestBodyJsonV3(@RequestBody HelloData helloData) {
        // HelloData로 바로 변환
        // log.info("messageBody = {}", messageBody);
        // HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("username = {}, age = {}",helloData.getUsername(), helloData.getAge());

        return "ok";
    }

    @ResponseBody
    @PostMapping("/request-body-json-v4")
    // @RequestBody 생략 시 ModelAttribute로 읽어 요청 파라미터(form, queryString)을 읽게 된다.
    public String requestBodyJsonV4(HttpEntity<HelloData> httpEntity) {
        // HelloData로 바로 변환
        // log.info("messageBody = {}", messageBody);
        // HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        HelloData data = httpEntity.getBody();
        // body에서 객체를 들고 온다.
        log.info("username = {}, age = {}",data.getUsername(), data.getAge());

        return "ok";
    }

    @ResponseBody
    @PostMapping("/request-body-json-v5")
    // @RequestBody 생략 시 ModelAttribute로 읽어 요청 파라미터(form, queryString)을 읽게 된다.
    public HelloData requestBodyJsonV5(@RequestBody HelloData data) {
        // HelloData로 바로 변환
        // log.info("messageBody = {}", messageBody);
        // HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        // body에서 객체를 들고 온다.
        log.info("username = {}, age = {}",data.getUsername(), data.getAge());
        return data; // 객체 -> JSON 변환
    }
}
