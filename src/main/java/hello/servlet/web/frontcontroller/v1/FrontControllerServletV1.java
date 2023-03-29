package hello.servlet.web.frontcontroller.v1;

import hello.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV1", urlPatterns = "/front-controller/v1/*")
public class FrontControllerServletV1 extends HttpServlet {

    //urlPatterns = "/front-controller/v1/*" : /front-controller/v1 을 포함한 하위 모든 요청은 이 서블릿에서 받아들임

    //key는 url, value는 호출될 Controller => 어떤 url을 부르면 ControllerV1을 꺼내서 호출
    private Map<String, ControllerV1> controllerMap = new HashMap<>();

    public FrontControllerServletV1() {
        controllerMap.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllerMap.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerMap.put("/front-controller/v1/members", new MemberListControllerV1());
    } //매핑 정보 담아놓음, 어떤 Controller를 호출할지 찾음

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV1.service");

        String requestURI = request.getRequestURI();  //호출된 URI를 조회

        //ControllerV1(부모) controller = new MemberListControllerV1();(자식) 밑의 코드와 동일
        ControllerV1 controller = controllerMap.get(requestURI);  //controllerMap 에서 URI에 맞는 Controller 반환

        if(controller == null) {  //예외 처리, controllerMap 내에 맞는 Controller가 없으면 404 상태 코드 반환
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        controller.process(request, response);
        //조회가 잘 됐으면 value에 들어있는 객체 인스턴스의 process() 호출
    }
}
