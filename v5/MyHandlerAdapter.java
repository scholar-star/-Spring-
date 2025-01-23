package hello.servlet.web.frontcontroller.v5;

import hello.servlet.web.frontcontroller.ModelView;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.rmi.ServerException;

public interface MyHandlerAdapter {
    boolean supports(Object handler); // 어댑터가 지원하는지 여부
    ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws
            ServerException, IOException;
}
