package hello.servlet.web.frontcontroller.v2;

import hello.servlet.web.frontcontroller.MyView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ControllerV2 {

    MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}   //반환 타입이 MyView 이므로 Front Controller는 Controller 호출 결과로 MyView를 반환 받음
    //그리고 view.render() 호출하면 forward 로직을 수행해서 JSP가 실행됨
    // 각각의 Controller는 MyView 객체를 생성만 해서 반환하면 된다!
