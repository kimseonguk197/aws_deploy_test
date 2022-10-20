package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    MemoryMemberRepository repository;
    MemberService memberService;

    @BeforeEach
    public void beforeEach(){
        repository = new MemoryMemberRepository ();
        memberService = new MemberService(repository);
    }

    //메서드들이 실행될때마다 동작을 하는 콜백 메서드를 표시하는 어노테이션
    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    void 회원가입() {
        //given

        Member member = new Member();
        member.setName("seonguk");
        //when
        Long resultId = memberService.join(member);

        //then
        assertThat(memberService.findOne(resultId).get().getName()).isEqualTo(member.getName());
    }

    @Test
    //예외1보다 아래 예외2 방법의 사용추천
    void 중복_회원_예외1() {
        //given

        Member member1 = new Member();
        member1.setName("seonguk");
        Member member2 = new Member();
        member2.setName("seonguk");
        //when
       memberService.join(member1);
        try{
            memberService.join(member2);
            //정상적으로 join이 될수 없는 given 조건이므로, 강제 fail() 시킴
            fail();
        }catch (IllegalStateException e){
            //then
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }

    }

    @Test
    void 중복_회원_예외2() {
        //given

        Member member1 = new Member();
        member1.setName("seonguk");
        Member member2 = new Member();
        member2.setName("seonguk");
        //when
        memberService.join(member1);
        assertThrows(IllegalStateException.class, () -> memberService.join(member2) );
        //아래와 같은 상관없는 에러일 경우엔 에러
        //assertThrows(NullPointerException.class, () -> memberService.join(member2) );

        //문구를 검증하고 싶을때는
//        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2) );
//        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");


    }
    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}