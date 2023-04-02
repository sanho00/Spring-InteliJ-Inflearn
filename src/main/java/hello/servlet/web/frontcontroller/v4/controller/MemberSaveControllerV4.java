package hello.servlet.web.frontcontroller.v4.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.v4.ControllerV4;

import java.util.Map;

public class MemberSaveControllerV4 implements ControllerV4 {

    private final MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        String username = paramMap.get("username");  //파라미터에서 값 꺼냄
        int age = Integer.parseInt(paramMap.get("age"));

        Member member = new Member(username, age);  //비즈니스 로직 실행
        memberRepository.save(member);

        model.put("member", member);  //model에 put으로 값 넣어줌
        return "save-result";  //view name 리턴
    }
}
