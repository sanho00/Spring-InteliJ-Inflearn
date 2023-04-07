package hello.servlet.web.frontcontroller.v5;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import hello.servlet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;
import hello.servlet.web.frontcontroller.v5.adapter.ControllerV4HandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {

    //private Map<String, ControllerV4> controllerMap = new HashMap<>(); 기존 코드
    private final Map<String, Object> handlerMappingMap = new HashMap<>();
    //어떤 Controller도 받을 수 있도록 Object를 넣음
    private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();
    // adapter가 여러 개 담겨 있으므로 list에 넣어서 그 중에 하나를 골라 쓸 수 있어야 함

    public FrontControllerServletV5() {
        initHandlerMappingMap();
        initHandlerAdapters();
    }

    private void initHandlerMappingMap() {
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());
        
        //V4 추가
        handlerMappingMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members", new MemberListControllerV4());
    }

    private void initHandlerAdapters() {

        handlerAdapters.add(new ControllerV3HandlerAdapter());
        handlerAdapters.add(new ControllerV4HandlerAdapter());
    }
    //여기까지 핸들러 매핑과 어댑터 초기화(등록) 완료


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Object handler = getHandler(request);
        //요청이 오면 handlerMappingMap에서 URI에 맞는 handler 반환
        //MemberFormControllerV3 반환됨

        if(handler == null) {  //예외 처리, controllerMap 내에 맞는 Controller가 없으면 404 상태 코드 반환
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //handler를 처리할 수 있는 handler adapter를 찾아야 함

        // 컬렉션.iter 작성하면 for문 자동 생성해줌
        MyHandlerAdapter adapter = getHandlerAdapter(handler);  // hadnleradapter 찾아옴
        //ControllerV3HandlerAdapter 반환됨

        ModelView mv = adapter.handle(request, response, handler);  // handler 호출
        //어댑터의 handle() 통해 실제 어댑터 호출됨
        //얻배터는 handler를 호출하고 그 결과를 어댑터에 맞추어 반환한다.

        String viewName = mv.getViewName(); //viewName 가져옴
        MyView view = viewResolver(viewName);  //논리 이름으로 viewResolver 호출해서 view 얻음

        view.render(mv.getModel(), request, response);  //render() 호출해서 model 넘겨줌
    }

    private Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();  //호출된 URI를 조회
        return handlerMappingMap.get(requestURI);
    }

    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        for (MyHandlerAdapter adapter : handlerAdapters) {  //for문으로 handler를 다 살펴봄
            if(adapter.supports(handler)) {  //동일한 게 있다면 supports로, handler로 처리 가능한지 확인
                return adapter;  // adapter 반환
            }
        }
        throw new IllegalArgumentException("handler adapter를 찾을 수 없습니다. handler : " + handler);
    }

    private static MyView viewResolver(String viewName) {  //논리 이름을 실제 물리 이름으로 만들고 물리 경로 있는 MyView 반환
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
}
