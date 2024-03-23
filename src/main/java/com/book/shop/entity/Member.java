package com.book.shop.entity;

import com.book.shop.constant.Role;
import com.book.shop.dto.MemberFormDto;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.springframework.security.crypto.password.PasswordEncoder;



@Entity
@Table(name = "member")
@Getter@Setter
@ToString
public class Member extends BaseEntity{

    @Id
    @Column(name="member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    private String zipcode;

    private String streetadr;

    private String detailadr;

    @Enumerated(EnumType.STRING)
    private Role role;

    public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder){
        Member member = new Member();
        member.setName(memberFormDto.getName());
        member.setEmail(memberFormDto.getEmail());
        member.setZipcode(memberFormDto.getZipcode());
        member.setDetailadr(memberFormDto.getDetailadr());
        member.setStreetadr(memberFormDto.getStreetadr());
        String password = passwordEncoder.encode(memberFormDto.getPassword());
        member.setPassword(password);
        member.setRole(Role.USER);
        return member;
    }

}