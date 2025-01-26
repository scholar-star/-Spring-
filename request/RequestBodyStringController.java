package hello.springmvc.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {
    @PostMapping("/request-body-string-v1")
    public void requestBodyStringV1(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8); //UTF-8로 변환하여 copy

        log.info("messageBody = {}",messageBody);

        response.getWriter().write("ok");
    }

    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter)
            throws ServletException, IOException {
        // inputStream으로 읽어들이기
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8); //UTF-8로 변환하여 copy

        log.info("messageBody = {}",messageBody);

        responseWriter.write("ok"); // writer로 화면에 작성
    }

    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(RequestEntity<String> httpEntity) // http객체(들어있는 Type)
            throws ServletException, IOException {
        // inputStream으로 읽어들이기
        // String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8); //UTF-8로 변환하여 copy
        String messageBody = httpEntity.getBody();
        log.info("messageBody = {}",messageBody);

        // responseWriter.write("ok"); // writer로 화면에 작성
        return new ResponseEntity<>("ok", HttpStatus.CREATED);
    }

    @ResponseBody
    @PostMapping("/request-body-string-v4")
    public String requestBodyStringV4(@RequestBody String messageBody) // http객체(들어있는 Type)
            throws ServletException, IOException {
        // inputStream으로 읽어들이기
        // String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8); //UTF-8로 변환하여 copy
        //String messageBody = httpEntity.getBody();
        log.info("messageBody = {}",messageBody);

        // responseWriter.write("ok"); // writer로 화면에 작성
        // return new ResponseEntity<>("ok", HttpStatus.CREATED)
        return "ok";
    }
}
