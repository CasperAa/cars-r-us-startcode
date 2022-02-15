package kea.sem3.jwtdemo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import kea.sem3.jwtdemo.entity.Member;
import kea.sem3.jwtdemo.entity.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemberResponse {

    String username;
    String email;
    String password;
    String firstName;
    String lastName;
    String street;
    String city;
    String zip;
    List <String> roleNames;


    //Only meant for admins
    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss")
    LocalDateTime created;

    @UpdateTimestamp
    LocalDateTime updated;

    Boolean isApproved; //Boolean is an object - boolean is a primitive type

    //Number between 0 and 10, ranking the customer
    Byte ranking; //Byte is an object - byte is a primitive type

    //Meant to be used as response when new users are created
    public MemberResponse(String username, LocalDateTime created, List<Role> roleList){
        this.created = created;
        this.roleNames = roleList.stream().map(role->role.toString()).collect(Collectors.toList());
        this.username = username;
    }

    //All data response
    public MemberResponse(Member member, boolean includeAll) {
        this.username = member.getUsername();
        this.email = member.getEmail();
        this.firstName = member.getFirstName();
        this.lastName = member.getLastName();
        this.street = member.getStreet();
        this.city = member.getCity();
        this.zip = member.getZip();
        if(includeAll) {
            this.email = member.getEmail();
            this.isApproved = member.isApproved();
            this.updated = member.getEdited();
            this.ranking = member.getRanking();
        }
    }

    public static List<MemberResponse> getMembersFromEntities(List<Member> members) {
        return members.stream().map(member -> new MemberResponse(member, false)).collect(Collectors.toList());
    }
}