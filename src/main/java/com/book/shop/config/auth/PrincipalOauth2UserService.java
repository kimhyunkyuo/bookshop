package com.book.shop.config.auth;

import com.book.shop.constant.Role;
import com.book.shop.entity.Member;
import com.book.shop.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);

        String provider = userRequest.getClientRegistration().getRegistrationId();
        String providerId = oAuth2User.getAttribute("sub");
        String name = provider + "_" + providerId;

        String uuid = UUID.randomUUID().toString().substring(0, 7);
        String password = bCryptPasswordEncoder.encode("패스워드" + uuid);

        String email = oAuth2User.getAttribute("email");
        Role role = Role.SOCIAL;

        Member byUserName = memberRepository.findByEmail(email);

        //DB에 없는 사용자라면 회원가입처리
        if(byUserName == null){
            byUserName = Member.oauth2Register()
                    .password(password).email(email).role(role)
                    .provider(provider).providerId(providerId)
                    .build();
            memberRepository.save(byUserName);
        }

        return new PrincipalDetails(byUserName, oAuth2User.getAttributes());
    }
}
