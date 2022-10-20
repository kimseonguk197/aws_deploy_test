//package hello.hellospring.repository;
//
//import hello.hellospring.domain.Member;
//
//import java.util.List;
//import java.util.Optional;
//
//public interface MemberRepository {
//    Member save(Member member);
//    //null이 반환될 경우를 대비하여, Optional로 감싸는 처리(java8부터)
//    Optional<Member> findById(Long id);
//    Optional<Member> findByName(String name);
//    List<Member> findAll();
//}
