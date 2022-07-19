package com.example.spring.service;

import com.example.spring.model.UserVo;
import com.example.spring.repository.MemberRepository;
import com.example.spring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

// 통합 테스트와 단위 테스트를 잘 구분할줄 알아야 한다.
@SpringBootTest // 스프링 컨테이너와 테스트를 함께 실행한다.
@Transactional // 테스트 시작 전에 트랜잭션을 시작하고, 테스트 완료 후에 항상 롤백을 한다. 이렇게 하면 DB에 데이터가 남지 않으므로 다음 테스트에 영향을 주지 않는다.
class MemberServiceIntegrationTest {

   @Autowired
   MemberService memberService;
   @Autowired
   MemberRepository memberRepository;

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

      /*  try {
            memberService.join(user2);
            //fail();
        }catch (IllegalStateException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.123");
        }*/


        //then
    }
}