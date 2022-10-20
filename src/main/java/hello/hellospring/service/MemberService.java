package hello.hellospring.service;

import hello.hellospring.domain.Member;
//import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class MemberService {

    private final MemoryMemberRepository repository;

    //@Autowired
    public MemberService(MemoryMemberRepository repository) {
        this.repository = repository;
    }


    /**
     * 회원 가입
     */
    public Long join(Member member){
        validateDupMember(member);

        repository.save(member);
        return member.getId();
    }

    private void validateDupMember(Member member) {
        repository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체회원조회
     */
    public List<Member> findMembers(){
        return repository.findAll();
    }

    /**
     * 회원조회
     */
    public Optional<Member> findOne(Long memberId){
        return repository.findById(memberId);
    }
}
