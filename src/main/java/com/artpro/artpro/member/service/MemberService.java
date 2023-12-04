package com.artpro.artpro.member.service;

import com.artpro.artpro.member.dto.LoginRequest;
import com.artpro.artpro.member.dto.RegisterRequest;
import com.artpro.artpro.member.dto.TokenResponse;
import com.artpro.artpro.member.entity.Member;
import com.artpro.artpro.member.jwt.JwtTokenProvider;
import com.artpro.artpro.member.mapper.MemberMapper;
import com.artpro.artpro.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void create(RegisterRequest dto) {
        if (!dto.getPassword().equals(dto.getCheckPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }
        String password = passwordEncoder.encode(dto.getPassword());
        Member member = memberMapper.mapToEntity(dto, password);
        memberRepository.save(member);
    }

    @Transactional
    public TokenResponse login(LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken authenticationToken = loginRequest.toAuthentication();
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        Member member = memberRepository.findByEmail(loginRequest.getEmail()).orElseThrow();
        return jwtTokenProvider.generateToken(authentication, member.getId());
    }
}