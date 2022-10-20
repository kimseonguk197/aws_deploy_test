package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
    public class MemoryMemberRepository{
    //실무에서는 동시성 문제로 인해 ConcurrentHashMap을 사용
    private static Map<Long, Member> store = new HashMap<>();
    //실수에서는 AtomicLong 사용
    private static long sequence = 0L;

//    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(sequence, member);
        return member;
    }

//    @Override
    public Optional<Member> findById(Long id) {
//        Optional<T> 클래스는 Integer나 Double 클래스처럼 'T'타입의 객체를 포장해 주는 래퍼 클래스(Wrapper class)
//        of() 메소드나 ofNullable() 메소드를 사용하여 Optional 객체를 생성
        //null일 가능성이 있는 경우에 ofNullable
        return Optional.ofNullable(store.get(id));
    }

//    @Override
    public Optional<Member> findByName(String name) {
        //람다 방식 코드
        //filter() 메소드는 해당 스트림에서 주어진 조건(predicate)에 맞는 요소만으로 구성된 새로운 스트림을 반환
        //forEach() 메소드는 해당 스트림의 요소를 하나씩 소모해가며 순차적으로 요소에 접근하는 메소드
        //같은 스트림으로는 forEach() 메소드를 한 번밖에 호출할 수 없습니다.
        //같은 데이터에서 또 다른 스트림을 생성하여 forEach() 메소드를 호출하는 것은 가능
        return store.values().stream()
                .filter(m -> m.getName().equals(name))
                .findAny();
    }

//    @Override
    public List<Member> findAll() {
        //java실무에서는 편의상 list를 많이 사용하므로, list형태로 반환
//        map의 values()
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
