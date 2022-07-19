package com.example.spring.service;

import com.example.spring.model.UserVo;
import static org.assertj.core.api.Assertions.*;

import com.example.spring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void join() {
        //given
        UserVo user = new UserVo();
        user.setName("Hello");

        //when
        Long saveId = memberService.join(user);

        //then
        UserVo findMember = memberService.findOne(saveId).get();
        assertThat(user.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        //given
        UserVo user1 = new UserVo();
        user1.setName("spring");

        UserVo user2 = new UserVo();
        user2.setName("spring");

        //when
        memberService.join(user1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(user2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");


    }

    @Test
    void findUsers() {
    }

    @Test
    void findOne() {
    }
}