package com.example.spring.service;

import com.example.spring.model.UserVo;
import com.example.spring.repository.MemberRepository;
import com.example.spring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// ctrl + shift + T
@Transactional // Jpa는 모든 데이터가 변경될 때 트랜잭션 안에서 실행되어야 한다.
public class MemberService {

    private final MemberRepository memberRepository;
    @Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    // 회원 가입
    public Long join(UserVo user){
        // 중복 회원 검증
        validateDuplicateUser(user);

        memberRepository.save(user);
        return user.getId();
    }

    private void validateDuplicateUser(UserVo user) {
        memberRepository.findByName(user.getName())
                .ifPresent(u ->{
            throw new IllegalStateException("이미 존재하는 회원입니다.");
            });
    }

    // 전체 회원 조회
    public List<UserVo> findUsers(){
        return memberRepository.findAll();
    }

    public Optional<UserVo> findOne(Long userId){
        return memberRepository.findById(userId);
    }


}
