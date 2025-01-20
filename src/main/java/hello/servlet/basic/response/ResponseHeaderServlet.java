package hello.servlet.basic.response;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        // 응답 1번째 - status-line
        response.setStatus(HttpServletResponse.SC_OK); // 200

        // response-headers
        //response.setHeader("Content-Type", "text/plain;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache"); // 캐시 무효화
        response.setHeader("Pragma", "no-cache"); // 이전 캐시도 없앰
        response.setHeader("my-header","hello");

        content(response);
        cookie(response);
        redirect(response);
        // header 편의 메서드
        PrintWriter writer = response.getWriter();
        writer.println("ok");
    }

    private void content(HttpServletResponse response) {
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        // header의 세부 타입 지정
    }
    
    private void cookie(HttpServletResponse response) {
        Cookie cookie = new Cookie("myCookie", "good");
        cookie.setMaxAge(600); // 600초 수명
        response.addCookie(cookie); // 쿠키 추가
    }

    private void redirect(HttpServletResponse response) throws IOException {
        // 302 redirect

//        response.setStatus(HttpServletResponse.SC_FOUND); // 영구 리다이렉트
//        response.setHeader("Location", "/basic/hello-form.html");
        response.sendRedirect("/basic/hello-form.html");
        // 리다이렉트와 Location
        // 지정을 한번에
        // Location으로 이동할 URI 지정
    }
}
