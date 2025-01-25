package hello.springmvc.basic.requestmapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class MappingController {
    @RequestMapping(value = {"/hello-basic","hello-go"}, method = RequestMethod.GET) // 복수 URL 가능
    public String helloBasic() {
        log.info("hello-basic");
        return "ok";
    }

    @RequestMapping(value = "/mapping-get-v1", method = RequestMethod.GET)
    public String mappingGetV1() {
        log.info("mapping-get-v1");
        return "ok";
    }

    @GetMapping(value = "/mapping-get-v2")
    public String mappingGetV2() {
        log.info("mapping-get-v2");
        return "ok";
    }

    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable String userId) {
        // 경로변수이용
        log.info("mappingPath userId = {}", userId);
        return "ok";
    }

    @GetMapping("/mapping/users/{userId}/orders/{order}")
    public String mappingPath(@PathVariable String userId, @PathVariable String order) {
        // 경로변수이용
        log.info("mappingPath userId = {}", userId);
        log.info("mappingPath order = {}", order);
        return "ok";
    }

    @GetMapping(value = "/mapping-param", params = "mode=debug") // ?mode=debug
    public String mappingParam() {
        log.info("mappingParam");
        return "ok";
    }

    @GetMapping(value = "/mapping-header", headers = "mode=debug") // {mode : debug}
    public String mappingHeader() {
        log.info("mappingheader");
        return "ok";
    }

    @PostMapping(value = "/mapping-consume", consumes = "application/json") // JSON - consumes 파라미터
    public String mappingConsume() {
        log.info("mappingConsume");
        return "ok";
    }

    @PostMapping(value = "/mapping-produce", produces = "text/html") // html/text - produces
    public String mappingProduce() {
        log.info("mappingProduce");
        return "ok";
    }
}
