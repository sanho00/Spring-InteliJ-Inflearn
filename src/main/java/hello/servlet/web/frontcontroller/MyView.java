package hello.servlet.web.frontcontroller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class MyView {

    private String viewPath; // "/WEB-INF/views/~~~.jsp"

    public MyView(String viewPath) {
        this.viewPath = viewPath;
    }

    public void render(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }

    public void render(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        modelToRequestAttribute(model, request);  // 1. render()가 호출되면 model에 있는 값을 다 꺼냄
        //model에 있는 데이터를 request attribute 로 바꾼다.
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response); // 3. jsp 포워드, jsp가 request.getAttribute()로 데이터 조회함
    }

    //RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
    //        dispatcher.forward(request, response);
    // 위의 코드가 첫번째 render와 중복되는 코드이므로 render(request, response); << 이렇게 첫번째 render를 호출할 수 있을 것 같다

    private static void modelToRequestAttribute(Map<String, Object> model, HttpServletRequest request) {
        model.forEach((key, value) -> request.setAttribute(key, value));  // 2.request에 setAttribute로 model값 다 넣음
    }
}
