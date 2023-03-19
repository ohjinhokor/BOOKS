package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {

    @PostMapping("/request-body-string-v1")
    public void requestBodyString(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody = {}", messageBody);
    }

    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws IOException {
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messageBody = {}", messageBody);
        responseWriter.write("ok");
    }

    // @RequestParam, @ModelAttribute와 HttpEntity는 관계가 없음
    // HttpEntity는 Http Header와 body의 정보를 편리하게 조회한다.
    // 메시지 바디나 헤더의 정보를 직접 조회 할 수 있다.
    // 메세지 바디나 헤더에 정보를 넣어서 response를 할 수도 있다. view는 조회 X
    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) throws IOException {
        String messageBody = httpEntity.getBody();
        log.info("messageBody = {}", messageBody);

        return new HttpEntity<>("ok");
    }

    // 스프링 MVC 내부에서 HTTP 메세지 바디를 읽어서 문자나 객체로 변환해서 전달해주는데, 이 때 'HTTP 메시지 컨버터'를 사용한다.
    @PostMapping("/request-body-string-v0")
    public HttpEntity<String> requestBodyStringV0(RequestEntity<String> httpEntity) throws IOException {
        String messageBody = httpEntity.getBody();
        log.info("messageBody = {}", messageBody);

        return new ResponseEntity<String>("ok", HttpStatus.CREATED);
    }

    // @RequestBody와 @ResponseBody의 사용
    @PostMapping("/request-body-string-v4")
    @ResponseBody
    public String requestBodyStringV4(@RequestBody String messageBody) throws IOException {
        log.info("messageBody = {}", messageBody);
        return "ok";
    }

    // 요청 파라미터와 HTML Form데이터를 조회하는 기능 : @RequestParam, @ModelAttribute
    // HTTP Message Body를 조회하는 기능 : @RequestBody
}
