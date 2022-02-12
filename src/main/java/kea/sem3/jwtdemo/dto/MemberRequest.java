package kea.sem3.jwtdemo.dto;

import kea.sem3.jwtdemo.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberRequest {
    @Enumerated(EnumType.STRING)
    private Role role;
    private String username;
    private String email;
    private boolean enabled;
    private String password;
    private int ranking;
    private String firstname;
    private String lastname;
    private String street;
    private String city;
    private int zip;
}