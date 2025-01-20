package hello.servlet.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.basic.HelloData;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StreamUtils;

import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "requestBodyJsonServlet", urlPatterns = "/request-body-json")
public class RequestBodyJsonServlet extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        ServletInputStream inputStream = request.getInputStream(); // JSON도 text의 일부
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        System.out.println("messageBody = "+messageBody);

        HelloData hellodata = objectMapper.readValue(messageBody, HelloData.class);
        // json을 mapping시켜 java 객체로 변환

        System.out.println("hellodata.username = "+hellodata.getUsername());
        System.out.println("helloData.age = "+hellodata.getAge());

        response.getWriter().write("ok");
    }
}
