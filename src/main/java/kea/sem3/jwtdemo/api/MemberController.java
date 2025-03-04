package kea.sem3.jwtdemo.api;

import kea.sem3.jwtdemo.dto.CarResponse;
import kea.sem3.jwtdemo.dto.MemberRequest;
import kea.sem3.jwtdemo.dto.MemberResponse;
import kea.sem3.jwtdemo.service.CarService;
import kea.sem3.jwtdemo.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/members")
public class MemberController {
    MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public ResponseEntity<List<MemberResponse>> getAllMembers(){
        return ResponseEntity.ok(memberService.getMembers());
    }

    @GetMapping("/fromtoken/user")
    @RolesAllowed({"USER","ADMIN"})
    public MemberResponse getAuthenticatedMember(Principal principal) {
        return (memberService.getMemberFromUsername(principal.getName()));
    }



    @GetMapping("/{username}")
    public MemberResponse getMemberFromUserName(@PathVariable String username){
        return (memberService.getMemberFromUsername(username));
    }

    @PostMapping()
    public MemberResponse AddMember(@RequestBody MemberRequest body){
        System.out.println("Hello");
        return null;
                //memberService.addMember(body);
    }

    @PutMapping("/{username}")
    public MemberResponse editMember(@RequestBody MemberRequest body, @PathVariable String username){
        return memberService.editMember(body, username);
    }

    @DeleteMapping("/{username}")
    public void deleteMember(@PathVariable String username){
        memberService.deleteMember(username);
    }



}