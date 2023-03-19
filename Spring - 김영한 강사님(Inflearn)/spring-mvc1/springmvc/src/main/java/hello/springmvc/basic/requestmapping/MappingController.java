package hello.springmvc.basic.requestmapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
public class MappingController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping({"/hello-basic", "/hello-basic2"}) // 2개 이상 가능
    public String helloBasic(){
        log.info("helloBasic");
        return "ok";
    }

    //GetMapping, PostMapping등 사용 가능
    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable String userId, @PathVariable Long orderId) {
        log.info("mappingPath userId={}, orderId={}", userId, orderId);
        return "ok";
    }

    @GetMapping(value = "/mapping-param", params = "mode=debug") // localhost:8080/mapping-param?mode=debug 이런식으로 queryString에 mode=debug를 넣어줘야 함
    public String mappingParam(){
        log.info("mappingParam");
        return "ok";
    }

    @GetMapping(value = "/mapping-header", headers = "mode=debug") // header에 mode = debug를 넣어줘야 함
    public String mappingHeader(){
        log.info("mappingHeader");
        return "ok";
    }

    @PostMapping(value = "/mapping-consumes", consumes = "application/json") // Content-Type이 application/json인 경우에만 동작함
    public String mappingConsumes(){
        log.info("mappingConsumes");
        return "ok";
    }

    @PostMapping(value = "/mapping-produce", produces = "text/html") // header에 Accept가 text/html인 경우만 가능
    public String mappingProduces() {
        log.info("mappingProduces");
        return "ok";
    }
}
