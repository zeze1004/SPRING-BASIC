package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);   // 저장할 때 마다 id값 하나 씩 증가시킴
        store.put(member.getId(), member); // map이 store에 저장
        return member;

    }

    @Override
    public Optional<Member> findById(Long id) {
        // id가 없으면 null 값 반환돼어 에러
        // Optional.ofNullable() 하면 null 값 처리해 줌
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        // member에서 같은 name이 나오면 그 이름 반환
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        // Map<Long, Member> store: store에 있는 values는 Member
        return new ArrayList<>(store.values());
    }

    // 데이터 삭제하는 함수
    public void clearStore() {
        store.clear();
    }
}
