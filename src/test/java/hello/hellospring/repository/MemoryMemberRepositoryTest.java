package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

//굳이 public으로 안해도됨.
class MemoryMemberRepositoryTest {
    //참조 변수의 타입 변환
    //추후 dbms도입후에 수월하게 변환하기 위함
    //MemberRepository repository = new MemoryMemberRepository ();

    MemoryMemberRepository repository = new MemoryMemberRepository ();
    //메서드들이 실행될때마다 동작을 하는 콜백 메서드를 표시하는 어노테이션
    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }
    //Test 어노테이션을 붙이면 run을 통해 아래 save메서드를 바로 실행할 수 있게 된다.
    @Test
    public void save(){

        Member member = new Member();
        member.setName("seonguk");
        repository.save(member);

        //.get()은 optional객체에서 값을 꺼내는 방법.
        //get을 쓰지 않으면 Member repoMember이게 아니라, Optional<Member> repoMember형태로 받아야함
        Member repoMember = repository.findByName(member.getName()).get();
        //확인 방법1.
        //System.out.println("result : " + ( repoMember == member));
        //방법2. : 매번 글자로 확인할 수는 없다. junit.jupiter의 assertions 사용
        //성공적이면 : 실행시, 정상적인 파란불. 실패시, 주황불 및 빨간에러
        //Assertions.assertEquals(member, repoMember );

        //방법3. : 매번 글자로 확인할 수는 없다. assertj 사용
        //성공적이면 : 실행시, 정상적인 파란불. 실패시, 주황불 및 빨간에러
        //Assertions.assertThat(member).isEqualTo(repoMember);
        //위 형태에서 add on-demand static import를 하면 편하게 사용가능
        assertThat(member).isEqualTo(repoMember);
    }


    @Test
    public void findByName(){

        Member member1 = new Member();
        member1.setName("seonguk1");
        repository.save(member1);

        Member repoMember = repository.findByName("seonguk1").get();

        assertThat(repoMember).isEqualTo(member1);
    }

    @Test
    public void findAll(){

        Member member1 = new Member();
        member1.setName("seonguk1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("seonguk2");
        repository.save(member2);

        List<Member> repoMembers = repository.findAll();
        assertThat(repoMembers.size()).isEqualTo(2);


    }
}
