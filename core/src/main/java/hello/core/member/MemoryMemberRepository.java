package hello.core.member;
import java.util.Map;
import java.util.HashMap;
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    // 동시성 문제가 있다면 Concurrency Map

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
        // map - put으로 elements 추가.
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
        // key로 반환
    }
}
