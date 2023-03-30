package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    //urlPatterns = "/front-controller/v1/*" : /front-controller/v1 을 포함한 하위 모든 요청은 이 서블릿에서 받아들임

    //key는 url, value는 호출될 Controller => 어떤 url을 부르면 ControllerV1을 꺼내서 호출
    private Map<String, ControllerV3> controllerMap = new HashMap<>();

    public FrontControllerServletV3() {
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
    } //매핑 정보 담아놓음, 어떤 Controller를 호출할지 찾음

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestURI = request.getRequestURI();  //호출된 URI를 조회

        ControllerV3 controller = controllerMap.get(requestURI);  //controllerMap 에서 URI에 맞는 Controller 반환

        if(controller == null) {  //예외 처리, controllerMap 내에 맞는 Controller가 없으면 404 상태 코드 반환
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //paramMap을 넘겨줘야함
        Map<String, String> paramMap = createParamMap(request);
        ModelView mv = controller.process(paramMap);

        String viewName = mv.getViewName(); //논리이름만 알 수 있음 ex)new-form
        MyView view = viewResolver(viewName);  //논리이름으로 viewResolver 호출
        view.render(mv.getModel(), request, response);
    }

    private static MyView viewResolver(String viewName) {  //논리 이름을 실제 물리 이름으로 만들고 물리 경로 있는 MyView 반환
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    private static Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        //request에서 모든 파라미터 name을 다 가져온다.
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
                                //key의 변수명                 key              value
        //paramName에 들어있는 value(모든 파라미터 정보)를 다 꺼내온다
        //iterator를 사용한 이유 : name과 age 뿐만 아니라 실제로 들어올 더 다양한 종류의 파라미터를 처리하기 위함
        // => 범용적으로 처리 가능하다.
        //forEachRemaining() : 모든 요소가 처리되거나 예외가 throw 될 때까지 각 나머지 요소에 대한 지정된 작업 수행
        return paramMap;
    }
}
