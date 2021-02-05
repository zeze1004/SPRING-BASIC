package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    // 회원가입
    public Long join(Member member) {
        // 증복 회원 검증
        extracted(member);


        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        Optional<Member> result = memberRepository.findByName(member.getName());
        // 값이 있으면 throw
        result.ifPresent(m -> { // meber를 m으로 받아서 값이 있는지 비교
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }
    // 전체 회원 조회
    public List<Member> findMembers() {
        // emberRepository.findAll()의 return 값은 list
        return memberRepository.findAll();
    }
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
