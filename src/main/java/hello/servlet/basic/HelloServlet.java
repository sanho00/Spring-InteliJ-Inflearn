package hello.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello") //name : 서블릿이름, urlPatterns : url 매핑
//name과 urlPatterns는 겹치면 안된다.
public class HelloServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
                                        throws ServletException, IOException {

        System.out.println("HelloServlet.service");  //단축키 soutm
        System.out.println("request = " + request);  //단축키 soutv
        System.out.println("response = " + response);

        String username = request.getParameter("username");
        System.out.println("username = " + username);

        response.setContentType("text/plain");      //단순 문자로 보낸다 message header
        response.setCharacterEncoding("utf-8");     //문자 인코딩 정보 message header
        response.getWriter().write("hello " + username);     //http messagebody에 들어감
    }
}
