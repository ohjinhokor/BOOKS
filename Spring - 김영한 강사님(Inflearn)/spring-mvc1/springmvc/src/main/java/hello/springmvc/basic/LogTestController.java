package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j // Slf4j 어노테이션을 사용하면 아래 LoggerFactory.getLogger를 호출할 필요 없음
@RestController
public class LogTestController {
//    private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest(){

        String name = "Spring";

        // 잘못된 로그 사용 방법 예시
        log.trace("trace my log="+name); // 이렇게 하면 안되는 이유는 trace 로그가 실제로 찍히던 안찍히던 관계 없이 trace my log와 name이 합쳐지는 연산은 무조건 일어나기 때문이다. 이는 성능에 영향을 준다.

        // 설정 파일에 어느 수준까지 로그를 남길지 정할 수 있음
        log.trace(" trace log={}", name); // 이거는 "trace log ={}"와 name을 파라미터로 넘긴다고 보면 됨. 따라서 함수가 실행되지 않으면 쓸데 없는 계산이 일어나지 않음.
        log.debug(" debug log={}", name);
        log.info(" info log={}", name);
        log.warn(" warn log={}", name);
        log.error(" error log={}", name);

        return "ok";
    }
}
