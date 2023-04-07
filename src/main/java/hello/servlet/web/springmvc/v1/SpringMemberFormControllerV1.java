package hello.servlet.web.springmvc.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller //Spring이 자동으로 스프링 빈으로 등록함, 어노테이션 기반 Controller로 인식함
//@Controller 대신 @Component @RequestMapping 작성해도 똑같이 동작함 
//@Component 빼고 클래스 내부에서 직접 스프링 빈 등록해줘도 똑같이 동작함
public class SpringMemberFormControllerV1 {

    @RequestMapping("/springmvc/v1/members/new-form") //요청 정보 매핑, 해당 URL이 호출되면 이 메서드 호출됨
    public ModelAndView process() { //ModelAndView : Model과 View 정보를 담아서 반환하면 됨
        return new ModelAndView("new-form");
    }
}
