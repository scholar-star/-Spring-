package hello.servlet.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "requestBodyStringServlet", urlPatterns = "/request-body-string")
public class RequestBodyStringServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        ServletInputStream inputStream = request.getInputStream(); // raw - textdata 입력
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8); // 인코딩 방식을 알려주어야 한다.

        System.out.println("messageBody = "+messageBody); // 메세지 출력
        
        response.getWriter().write("ok"); //  브라우저에 작성
    }
}
