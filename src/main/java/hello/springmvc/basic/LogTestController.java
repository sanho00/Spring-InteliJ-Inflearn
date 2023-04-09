package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j //롬복이 자동으로 로그 선언해줌
@RestController //문자를 리턴하면 String 데이터가 바로 반환됨, @Controller는 View 이름이 반환
public class LogTestController {

    //private final Logger log = LoggerFactory.getLogger(getClass()); //getClass() : 현재 클래스 지정

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";

        //레벨 순서
        log.trace("trace log={}", name);
        log.debug("debug log={}", name); //디버그할 때 개발 서버에서 보는 것
        log.info("info log = {}", name); //운영 시스템에서도 봐야할 정보, 중요한 비즈니스 정보
        log.warn("warn log={}", name); //경고
        log.error("error log={}", name); //에러

        return "ok";
    }
}
