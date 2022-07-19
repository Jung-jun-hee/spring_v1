package com.example.spring.repository;

import com.example.spring.model.UserVo;

import java.util.List;
import java.util.Optional;

// 회원 인터페이스
public interface MemberRepository {
    // 회원 저장
    UserVo save(UserVo user);

    //회원 정보 조회
    Optional<UserVo> findById(Long id);
    Optional<UserVo> findByName(String name);

    // 모든 회원 조회
    List<UserVo> findAll();
}
