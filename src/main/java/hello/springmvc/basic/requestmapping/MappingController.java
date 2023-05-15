package hello.springmvc.basic.requestmapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController     //반환 값으로 뷰를 찾는 것이 아니라 String을 HTTP 메시지 바디에 바로 입력한다.
@Slf4j
public class MappingController {

    @RequestMapping(value = "/hello-basic")     //이 url을 불러오면 이 메서드가 호출된다
    public String helloBasic() {
        log.info("helloBasic");
        return "ok";
    }

    @RequestMapping(value = "/mapping-get-v1", method = RequestMethod.GET)
    public String mappingGetV1() {
        log.info("mappingGetV1");
        return "ok";
    }

    @GetMapping(value = "/mapping-get-v2")
    public String mappingGetV2() {
        log.info("mapping-get-v2");
        return "ok";
    }

    /*
    * PathVariable 사용하여 리소스 경로에 식별자 넣어서 사용 (템플릿화)
    * 변수명이 같으면 생략 가능
    * @PathVariable("userId") String userId -> @PathVariable String userId
    * /mapping/userA
    * */
    @GetMapping(value = "/mapping/{userId}")
    public String mappingPath(@PathVariable("userId") String data)  {
        log.info("mappingPath userId={}", data);
        return "ok";
    }

    @GetMapping(value = "/mapping/users/{userId}/orders/{orderId}")
    public String mappingPathV2(@PathVariable String userId, @PathVariable Long orderId) {
        log.info("mappingPath userId={}, orderId={}", userId, orderId);
        return "ok";
    }

    //특정 파라미터 조건 매핑
    // params="mode=debug" 생략하면 호출 안됨
    @GetMapping(value = "/mapping-param", params = "mode=debug")
    public String mappingParam() {
        log.info("mappingParam");
        return "ok";
    }

    //특정 헤더로 추가 매핑
    //파라미터 매핑과 비슷하지만 HTTP 헤더를 사용하는 것이 차이점
    @GetMapping(value = "/mapping-header", headers = "mode=debug")
    public String mappingHeader() {
        log.info("mappingHeader");
        return "ok";
    }

    //Content-Type 헤더 기반 추가 매핑
    //Content-Type이 Json 형식일 때만 호출 가능
    @PostMapping(value = "/mapping-comsume", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String mappingConsumes() {
        log.info("mappingConsumes");
        return "ok";
    }

    //HTTP Accept 헤더 기반 요청 매핑
    @PostMapping(value = "/mapping-produce", produces = MediaType.TEXT_HTML_VALUE)
    public String mappingProduces() {
        log.info("mappingProduces");
        return "ok";
    }
}
