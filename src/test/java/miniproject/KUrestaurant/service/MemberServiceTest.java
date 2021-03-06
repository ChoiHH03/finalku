package miniproject.KUrestaurant.service;

import miniproject.KUrestaurant.domain.Member;
import miniproject.KUrestaurant.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    public void 회원가입() {
        Member member = new Member();
        member.setLoginId("member");
        Long id = memberService.join(member);

        assertEquals(memberRepository.findOne(id), member);

    }

    @Test
    public void 중복아이디오류() {
        Member member1 = new Member();
        Member member2 = new Member();
        member1.setLoginId("member");
        member2.setLoginId("member");
        memberService.join(member1);
        assertThrows(IllegalStateException.class, () ->
                memberService.join(member2));
    }
}