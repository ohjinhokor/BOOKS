package hello.springmvc.basic.request;

// 강의에 앞서 꼭 기억해야 할 것
// 클라이언트에서 서버로 요청 데이터를 전달할 때는 주로 3가지의 방법을 사용한다.
// 1. Get으로 요청하면서 query parameter(query string) 사용
// 2. Post로 요청하면서 HTML Form데이터 사용 (content - type을 application/x-www-form-urlencoded로 한다)
// 3. HTTP message body에 데이터를 직접 담아서 요청(요즘은 주로 json 형식을 이용)

// request.getParameter()를 사용하면 1번과 2번을 다 조회할 수 있음, 또는 @RequestParam 사용


import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username = {}, age = {}", username, age);

        response.getWriter().write("ok");
    }

    // 현재 스프링은 @RequestParam을 붙이지 않더라도 요청 파라미터 key 값과 parameter의 변수 명이 같으면 자동으로 매핑해줌
    // 주관적인 생각으로는 @RequestParam을 굳이 안붙일 필요가 있나 싶긴 함. 오히려 가독성이 붙이는게 더 좋은 듯
    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    // 특정 requestParam이 필수인지 아닌지 알려줄 수 있음
    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
        @RequestParam(required = true) String username,
        @RequestParam(required = false) Integer age) // 아무것도 넣지 않으면 null이 들어간다,  * int가 아닌 Integer가 들어가는 이유는 int는 원시 타입이므로 null이 들어갈 수 없기 때문이다.
    {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
        @RequestParam(required = true, defaultValue = "guest") String username, // defaultValue가 있으면 required=true는 사실상 의미가 없어진다.
        @RequestParam(required = false, defaultValue = "-1") int age) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {
        log.info("username={}, age={}", paramMap.get("username"),
            paramMap.get("age")); // 만약 request에 key가 age인 데이터가 없으면 null이 나옴
        return "ok";
    }

    // HelloData 객체가 생성되고, 요청 파라미터의 값도 모두 들어가 있다.
    // @ModelAttribute가 있으면 다음을 실행한다.
    // 1. HelloData 객체를 생성 2. 요청 파라미터의 이름으로 HelloData 객체의 프로퍼티를 찾는다. 해당 프로퍼티의 setter를 호출해서 파라미터의 값을 입력(바인딩)한다.(예를 들어 username이면 setUsername() 메서드를 찾아서 호출하여 값을 입력한다.)

    // 아래의 경우 @ModelAttribute를 생략해도 실행이 잘 된다.
    // 어노테이션이 생략되어 있는 경우 2가지 경우의 수가 있다. String, int, Integer처럼 단순 타입의 경우 @RequestParam이 적용되고, argument Resolver에서 지정해둔 타입은 argument Resolver에서 적혀있는대로 처리된다. 그리고 나머지는 모두 @ModelAttribute가 적용된다.
    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }
}
