package hello.springmvc.basic.requestmapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

@RestController
public class MappingController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/hello-basic")
    public String helloBasic() {
        log.info("helloBasic");
        return "ok";
    }

    @RequestMapping (value = "/mapping-get-v1", method = RequestMethod.GET)
    public String mappingGetV1() {
        log.info("mapping-get-V1");
        return "ok";
    }

    @GetMapping ("/mapping-get-v2")
    public String mappingGetV2() {
        log.info("mapping-get-V2");
        return "ok";
    }

    // 실행 : http://localhost:8080/mapping/userA
    //url에 값 넣어서 사용 가능
    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable("userId") String data) {
        log.info("mappingPath userId={}", data);
        return "ok";
    }

    // 실행 : http://localhost:8080/mapping/users/userA/orders/100
    // 출력 : mappingPath userId=userA, orderId=100
    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable String userId, @PathVariable int orderId) {
        log.info("mappingPath userId={}, orderId={}", userId, orderId);
        return "ok";
    }

    // 실행 : http://localhost:8080/mapping-param?mode=debug
    // mode=debug (파라미터 정보) 가 없으면 출력 안됨.
    // 특정 파라미터가 있거나 없는 조건 추가 가능. 잘 사용 안 함
    @GetMapping(value = "/mapping-param", params = "mode=debug")
    public String mappingParam() {
        log.info("mappingParam");
        return "ok";
    }

    //특정 헤더로 추가 매핑
    // 헤더에 키와 값을 넣어야 함
    @GetMapping(value = "/mapping-header", headers = "mode=debug")
    public String mappingHeader() {
        log.info("mappingHeader");
        return "ok";
    }

    // 미디어 타입 조건 매핑 - HTTP 요청 헤더의 Content-Type 기반
    // 헤더의 미디어 타입이 application/json 인 경우에만 호출됨
    // 컨트롤러가 소비하는 타입
    @PostMapping(value = "/mapping-comsume", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String mappingConsumes() {
        log.info("mappingConsumes");
        return "ok";
    }

    // 미디어 타입 조건 매핑 - HTTP 요청 헤더의 Accept 기반
    // 컨트롤러가 생산해내는 타입
    // 헤더의 Accept 에 값 넣어 줘야 함
    @PostMapping(value="/mapping-produce", produces = MediaType.TEXT_HTML_VALUE)
    public String mappingProduces() {
        log.info("mappingProduces");
        return "ok";
    }
}
