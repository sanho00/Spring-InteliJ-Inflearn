package hello.servlet.basic.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
                                            throws ServletException, IOException {
        // [status-line]
        response.setStatus(HttpServletResponse.SC_OK);

        // [response-header]
        response.setHeader("Content-Type", "text/plain;charset=utf-8");
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); //캐시 무효화
        response.setHeader("Pragma", "no-cache");  //과거 캐시까지 삭제
        response.setHeader("my-header", "hello");  //사용자가 header 정의 가능

        // [Header 편의 메소드]
        //content(response);
        //cookie(response);
        //redirect(response);

        // [messsage-body]
        PrintWriter writer = response.getWriter();
        writer.println("안녕하세요");
    }

    // [Header 편의 메소드]
    private void content(HttpServletResponse response) {
        response.setContentType("text/plain");  //위에서처럼 내가 직접 쓰지 않고 메소드로 자동 생성 가능
        response.setCharacterEncoding("utf-8");
    }

    private void cookie(HttpServletResponse response) {
        Cookie cookie = new Cookie("myCookie", "good");
        cookie.setMaxAge(600);  //600초 동안 유효
        response.addCookie(cookie);
    }

    private void redirect(HttpServletResponse response) throws IOException {
        //Status Code 302
        //Location : /basic/hello-form.html
        //response.setStatus(HttpServletResponse.SC_FOUND);  //302로 상태 코드 설정
        response.sendRedirect("/basic/hello-form.html");  //이거 하나만 쓰면 된다
    }
}
