package miniproject.KUrestaurant.service;

import lombok.RequiredArgsConstructor;
import miniproject.KUrestaurant.domain.Member;
import miniproject.KUrestaurant.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        Optional<Member> loginMember = memberRepository.findByLoginId(member.getLoginId());
        if (loginMember.isPresent()) {
            throw new IllegalStateException("이미 존재하는 회원입니다");
        }
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}
