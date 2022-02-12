package kea.sem3.jwtdemo.api;

import kea.sem3.jwtdemo.dto.CarResponse;
import kea.sem3.jwtdemo.dto.MemberResponse;
import kea.sem3.jwtdemo.service.CarService;
import kea.sem3.jwtdemo.service.MemberService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/members")
public class MemberController {
    MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    //TODO: CRUD for member
    //Read -- All user types
    @GetMapping
    public List<MemberResponse> getMembers(){
        return memberService.getMembers();
    }

    @GetMapping("/{username}")
    public MemberResponse getMember(@PathVariable String username) throws Exception {
        return memberService.getMember(username, false);
    }



}