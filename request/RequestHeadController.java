package hello.springmvc.basic.request;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@Slf4j
@RestController
public class RequestHeadController {
    @RequestMapping("/headers")
    public String headers(HttpServletRequest request
    , HttpServletResponse response
    , HttpMethod httpMethod, Locale locale,
                          @RequestHeader MultiValueMap<String, String> headerMap,
                          @RequestHeader("host") String host,
                          @CookieValue(value = "myCookie", required = false) String Cookie) {

        log.info("request = {}",request);
        log.info("response = {}",response);
        log.info("httpMethod = {}",httpMethod);
        log.info("locale = {}",locale);
        log.info("headerMap = {}",headerMap);
        log.info("host = {}",host);
        log.info("Cookie = {}",Cookie);

        return "ok";
    }
}
