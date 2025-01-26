package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Controller
public class RequestParamControlller {
    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
        String username = req.getParameter("username");
        int age = Integer.parseInt(req.getParameter("age")); // GET, POST 상관없이 가능
        log.info("username:{}, age:{}", username, age);

        resp.getWriter().write("ok");
    }

    @ResponseBody // RestController와 동일 효과
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String username,
            @RequestParam("age") int memberAge
    ) {
        log.info("username = {}, age = {}",username, memberAge);
        return "ok";
    }

    @ResponseBody // RestController와 동일 효과
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username,
            @RequestParam int age
    ) {
        log.info("username = {}, age = {}",username, age);
        return "ok";
    }

    @ResponseBody // RestController와 동일 효과
    @RequestMapping("/request-param-v4")
    public String requestParamV4(
            String username,
            int age
    ) {
        log.info("username = {}, age = {}",username, age);
        return "ok";
    }

    @ResponseBody // RestController와 동일 효과
    @RequestMapping("/request-param-required")
    public String requestParamV5(
            @RequestParam(required = true, defaultValue = "guest") String username, // defaultValue : null/빈 문자일때 채워줌
            @RequestParam(required = false, defaultValue = "40") Integer age // String으로 들어와도 Type 변환시켜줌
    ) {
        // username= : 빈 문자로 인식
        log.info("username = {}, age = {}",username, age);
        return "ok";
    }

    @ResponseBody // RestController와 동일 효과
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {
        // username= : 빈 문자로 인식
        log.info("username = {}, age = {}",paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData data) {
        log.info("username = {}, age = {}",data.getUsername(), data.getAge());
        // 객체로 받은 뒤에 값을 넣는 작업을 해줌
        // ModelAttribute
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData data) {
        // argument resolver 제외하고 @ModelAttribute로 인식
        log.info("username = {}, age = {}",data.getUsername(), data.getAge());
        // 객체로 받은 뒤에 값을 넣는 작업을 해줌
        // ModelAttribute
        return "ok";
    }
}
