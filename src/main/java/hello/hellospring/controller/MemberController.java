package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MemberController {

    private final MemberService memberService;
    //1)필드주입 : @Autowired private MemberService memberService;
    //2)setter 주입 : 나중에 호출되어 주입하는 방식
    //@Autowired
    //public setMemberService(MemberService memberService){
    //    this.memberService = memberService;}
    //3)아래 방법은 생성자를 통한 주입방법
    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm memberForm){
        //html에서 넘어오는 key,value 값을 Spring에서 MemberForm을 사용하여, memberForm.setName을 해주게 된다.
        Member member = new Member();
        member.setName(memberForm.getName());
        memberService.join(member);
        //home화면으로 redirect
        return "redirect:/";
    }

    @GetMapping("/members")
    public String memberList(Model model){
        model.addAttribute("members", memberService.findMembers());
        return "members/memberList";
    }


}
