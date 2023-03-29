package hello.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemberRepositoryTest {  //JUint5부터는 Test에 public 없어도 됨

    //MemberRepository memberRepository = new MemberRepository(); 싱글톤이기 때문에 new 안됨
    //Spring은 싱글톤 보장해주기 때문에 싱글톤 사용할 필요 없음
    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach  //테스트 메소드 실행 이후에 수행되는 어노테이션, 테스트 순서를 위해 사용
    void afterEach() {  // Test가 끝날 때마다 테스트를 초기화하기 위함
        memberRepository.clearStore();
    }

    @Test
    void save() {
        //given : 이것이 주어졌을 때
        //인스턴스 생성
        Member member = new Member("hello given", 20);

        //when : 이것을 실행했을 때
        // 가상 데이터베이스(store)에 저장
        Member savedMember = memberRepository.save(member);

        //then : 결과가 이것이어야 함
        Member findMember = memberRepository.findById(savedMember.getId());
        //저장했던 member에서 id를 꺼내 member 객체를 직접 찾고
        Assertions.assertThat(findMember).isEqualTo(savedMember);
        //member가 동일한지 비교함 (Assertions는 assertj 사용)
    }

    @Test
    void findAll() {
        //given
        //인스턴스 생성
        Member member1 = new Member("member1", 20);
        Member member2 = new Member("member2", 30);
        //가상 데이터베이스에 저장
        memberRepository.save(member1);  //미리 저장
        memberRepository.save(member2);

        //when
        List<Member> result = memberRepository.findAll();  //전체 컬렉션 나옴

        //then
        assertThat(result.size()).isEqualTo(2);  // 예상되는 결과로 2명을 가지고 오는지 검증
        assertThat(result).contains(member1, member2);  //result 안에 member1과 member2 객체가 있는지 확인
    }
}
