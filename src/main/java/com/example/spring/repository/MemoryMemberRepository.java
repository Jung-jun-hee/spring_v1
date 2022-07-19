package com.example.spring.repository;

import com.example.spring.model.UserVo;
import org.springframework.stereotype.Repository;

import java.util.*;

// 상속받은 메서드 구현

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, UserVo> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public UserVo save(UserVo user) {
        user.setId(++sequence);
        store.put(user.getId(), user);
        return user;
    }

    @Override
    public Optional<UserVo> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<UserVo> findByName(String name) {
        return store.values().stream()
                .filter(user -> user.getName().equals(name))
                .findAny();
    }

    @Override
    public List<UserVo> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }

}
