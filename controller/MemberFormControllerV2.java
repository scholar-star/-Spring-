package hello.servlet.web.frontcontroller.v2.controller;

import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v2.ControllerV2;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.rmi.ServerException;

public class MemberFormControllerV2 implements ControllerV2 {
    @Override
    public MyView process(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String viewPath = "/WEB-INF/view/new-form.jsp"; // WEB-INF : 서버 내부에서만 호출(외부에서는 404)
        return new MyView(viewPath);
    }
}
