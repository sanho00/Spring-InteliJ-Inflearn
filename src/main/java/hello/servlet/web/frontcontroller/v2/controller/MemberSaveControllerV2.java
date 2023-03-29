package hello.servlet.web.frontcontroller.v2.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v2.ControllerV2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberSaveControllerV2 implements ControllerV2 {

    private MemberRepository memberRepository = MemberRepository.getInstance();
    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username"); //요청 정보 받아서 파싱
        int age = Integer.parseInt(request.getParameter("age"));

        Member member = new Member(username, age);  //member 객체 생성
        memberRepository.save(member);  // member 저장

        //Model에 데이터를 보관한다.
        request.setAttribute("member", member);  //request객체에 member 저장

        return new MyView("/WEB-INF/views/save-result.jsp");
    }
}
