package kea.sem3.jwtdemo.service;

import kea.sem3.jwtdemo.dto.MemberResponse;
import kea.sem3.jwtdemo.entity.Member;
import kea.sem3.jwtdemo.error.Client4xxException;
import kea.sem3.jwtdemo.repositories.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    public List<MemberResponse> getMembers() {
        List<Member> memberList = memberRepository.findAll();
        return MemberResponse.getMembersFromEntities(memberList);
    }

    public MemberResponse getMember(String username, boolean all) {
        Member member = memberRepository.findById(username).orElseThrow(()->new Client4xxException("Member not found"));
        return new MemberResponse(member, false);
    }

    //editMembers

    //addMember

    //Delete
}