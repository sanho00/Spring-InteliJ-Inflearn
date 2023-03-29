package hello.servlet.web.servletmvc;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="mvcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {
//Controller - controller를 거쳐서 view로 들어가야 한다. 그러려면 controller에 요청이 들어와야 함
    @Override  //고객 요청이 오면 service() 호출된다.
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String viewPath = "/WEB-INF/views/new-form.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath); //controller에서 view로 이동
        dispatcher.forward(request, response);  //다른 서블릿이나 JSP로 이동 가능, 서버 내부에서 다시 호출 발생
        //request에서 viewPath에 들어가있는 jsp 파일 다시 호출함
        //여기서 forward를 사용하는 이유는 viewPath에 해당하는 jsp로 이동하기 위함임
        //페이지 이동을 위해 굳이 호출을 2번 하는 redirect를 사용할 필요 없음
    }
}
