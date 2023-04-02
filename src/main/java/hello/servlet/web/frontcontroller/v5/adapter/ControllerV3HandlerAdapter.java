package hello.servlet.web.frontcontroller.v5.adapter;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV3HandlerAdapter implements MyHandlerAdapter {

    @Override
    public boolean supports(Object handler) {  //어댑터가 해당 컨트롤러를 처리할 수 있는지 판단하는 메서드
        return (handler instanceof ControllerV3);
        //ControllerV3 interface를 구현한 것이 넘어오면 true, 다른 게 넘어오면 false
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        //어댑터는 실제 컨트롤러를 호출하고, 그 결과로 ModelView를 반환해야 함
        //실제 컨트롤러가 ModelView를 반환하지 못 하면, 어댑터가 ModelView를 직접 생성해서라도 반환해야 함

        ControllerV3 controller = (ControllerV3) handler; //handler 호출
        //supports에서 한 번 걸렀기 때문에 캐스팅해도 됨
        Map<String, String> paramMap = createParamMap(request);  //결과를 paramMap에 넣음
        ModelView mv = controller.process(paramMap);
        //controller의 process() 호출하면서 실제 Controller 호출됨
        //반환 타입을 ModleView로 맞춤
        return mv;  // ModleView 반환
    }

    private static Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        //request에서 모든 파라미터 name을 다 가져온다.
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));

        return paramMap;
    }

}
