package kea.sem3.jwtdemo.service;

import kea.sem3.jwtdemo.dto.MemberRequest;
import kea.sem3.jwtdemo.dto.MemberResponse;
import kea.sem3.jwtdemo.entity.Car;
import kea.sem3.jwtdemo.entity.Member;
import kea.sem3.jwtdemo.entity.Role;
import kea.sem3.jwtdemo.error.Client4xxException;
import kea.sem3.jwtdemo.repositories.MemberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberService {
    MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    public List<MemberResponse> getMembers() {
        List<Member> memberList = memberRepository.findAll();
        return memberList.stream().map(member -> new MemberResponse(member, false)).collect(Collectors.toList());
    }

    public MemberResponse getMemberFromUsername(String username) {
        Member member = memberRepository.findById(username).orElseThrow(()->new Client4xxException("User not found", HttpStatus.NOT_FOUND));
        return new MemberResponse(member, false);
    }

    public MemberResponse addMember(MemberRequest body) {
        Member newMember = memberRepository.save(new Member (body));
        return new MemberResponse(newMember, false);
    }



    public MemberResponse editMember(MemberRequest body, String username) {
        Member member = memberRepository.findById(username).orElseThrow(()->new Client4xxException("Member not found"));
        member.setPassword((body.getPassword()));
        return new MemberResponse(memberRepository.save(member), false);
    }

    //Delete
    public void deleteMember(String username) {
        memberRepository.delete(memberRepository.findById(username).orElseThrow(()->new Client4xxException("Member not found")));
    }
}