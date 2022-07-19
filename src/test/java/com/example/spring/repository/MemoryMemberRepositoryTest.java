package com.example.spring.repository;

import com.example.spring.model.UserVo;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

public class MemoryMemberRepositoryTest {
    // Test를 수행할 때 Test하는 순서는 보장하지 않는다.
    // 그렇기 때문에 모든 테스트는 순서와 상관없이 따로 동작할 수 있도록 설계해야한다.
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void AfterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        UserVo user = new UserVo();
        user.setName("spring");

        repository.save(user);

        UserVo result = repository.findById(user.getId()).get();
        assertThat(user).isEqualTo(result);

    }

    @Test
    public void findByName(){
        UserVo user1 = new UserVo();
        user1.setName("spring1");
        repository.save(user1);

        UserVo user2 = new UserVo();
        user2.setName("spring2");
        repository.save(user2);

        UserVo result = repository.findByName("spring2").get();
        assertThat(result).isEqualTo(user2);

    }

    @Test
    public void findAll(){
        UserVo user1 = new UserVo();
        user1.setName("spring1");
        repository.save(user1);

        UserVo user2 = new UserVo();
        user2.setName("spring2");
        repository.save(user2);

        List<UserVo> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);

    }

}
