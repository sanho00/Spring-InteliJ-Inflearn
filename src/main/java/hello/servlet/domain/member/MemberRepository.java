package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//동시성 문제 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
public class MemberRepository {

    private static Map<Long, Member> store = new HashMap<>(); //싱글톤이기 때문에 static 안 써도 됨
    //store : 실제 DB를 연동하지 않고 DB를 본 따서 메모리에 저장하는 역할
    private static long sequence = 0L;  //id가 하나씩 증가하는 시퀀스로 쓰일 예정
    private static final MemberRepository instance = new MemberRepository();  //싱글톤
    public static MemberRepository getInstance() {
        return instance;  //무조건 getInstance()로 조회해야함
    }
    private MemberRepository() {
        //싱글톤은 private으로 생성자 막아야 함
    }

    public Member save(Member member) {  // id 저장
        member.setId(++sequence);  //시퀀스 값 1씩 증가 시키면서
        store.put(member.getId(), member);  // 멤버 저장
        return member;
    }

    public Member findById(Long id) {  //회원을 id로 찾을 수 있게 함
        return store.get(id);
    }

    public static List<Member> findAll() {
        return new ArrayList<>(store.values());
        // store에 있는 모든 value를 새로운 ArrayList에 넘겨줌
        // ArrayList를 값을 넣거나 조작해도 store에 있는 value list를 건들지 않기 위해 (store 자체를 보호)
    }

    public void clearStore() {  // store 모두 삭제, 테스트 에서만 사용
        store.clear();
    }
}
