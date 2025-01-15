package hello.core.member;
import hello.core.order.AppConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;

public class MemberServiceTest {
    MemberService memberService;
    // 추상화, 구체화 부분에 모두 의존한다(파헤칠 수 있다)

    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberservice();
    }

    @Test // annotation
    void join() {
        // given
        Member member = new Member(1L, "memberA", Grade.VIP);
        // when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);
        // then
        Assertions.assertThat(member).isEqualTo(findMember);
        // member에 대하여 findMember와 비교
    }
}
