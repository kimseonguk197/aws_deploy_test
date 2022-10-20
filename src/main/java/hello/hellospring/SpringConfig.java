//package hello.hellospring;
//
//import hello.hellospring.domain.Member;
//import hello.hellospring.repository.MemberRepository;
//import hello.hellospring.repository.MemoryMemberRepository;
//import hello.hellospring.service.MemberService;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class SpringConfig {
//
//    @Bean
//    public MemberService memberService(){
//        return new MemberService(memberRepository());
//    }
//
//    @Bean
//    public MemberRepository memberRepository(){
//        //여기서도 구현체를 등록해야함. interface는 등록안됨.
//        return new MemoryMemberRepository();
//    }
//}
