package hello.servlet.basic.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.basic.HelloData;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.rmi.ServerException;

@WebServlet(name = "responseJsonServlet", urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServerException, IOException {
        // Content-Type: application/json
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        HelloData hellodata = new HelloData();
        hellodata.setUsername("kim");
        hellodata.setAge(20);

        // {"username":"kim", "age":20}
        String result = objectMapper.writeValueAsString(hellodata);
        // 객체 -> String 변환
        response.getWriter().write(result);
        // 화면에다 String 형태로 작성
    }
}
