package hello.servlet.basic;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("HelloServlet.service");
        System.out.println("request = "+req);
        System.out.println("response = "+resp);

        String username = req.getParameter("username"); // query Parameter 불러오기 - key : value
        System.out.println("username = "+username);

        resp.setContentType("text/plain"); // 응답 type - text
        resp.setCharacterEncoding("UTF-8");
        // 문자 인코딩
        resp.getWriter().write("hello "+username); // 화면에다 작성해준다
    }
}
