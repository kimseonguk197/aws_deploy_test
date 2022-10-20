package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    //브라우저에서 /hello라고 들어오면 아래 메서드 호춮
    @GetMapping("hello")
    // 스프링이 model이라는 것을 만들어서 주입.
    public String hello(Model model){
        //그 model에 키:밸류를 세팅하여 화면에 전달
        model.addAttribute("name", "seonguk!!");
        //return 명은 templates 폴더 아래 html로 연결된다.
        return "hello";
    }

    @GetMapping("hello-mvc")
    //외부에서 parameter를 받을때는, @RequestParam 어노테이션사용
    //hello-mvc?name=nemo 이러한 형태로 호출
    //필수로 받을값이 아니라면, require=false 옵션을 주면된다.
    public String helloMvc(@RequestParam(value="name", required=false) String name, Model model){
        //넘겨받은 name을 value로 view에 넘겨줌
        model.addAttribute("name", name);
        return "hello-mvc";
    }

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(){
        return "hello-string ";
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam(value="name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }



    @GetMapping("hello-member")
    @ResponseBody
    public Member helloMember(@RequestParam(value="name") String name){
        Member member = new Member();
        MemoryMemberRepository memoryRepo = new MemoryMemberRepository();
        member.setName(name);
        memoryRepo.save(member);
        return member;
    }


}
