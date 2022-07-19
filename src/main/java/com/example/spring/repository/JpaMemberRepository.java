package com.example.spring.repository;

import com.example.spring.model.UserVo;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    // jpa 는 entity 매니저를 통해 모두 동작한다. db와 통신 관련된 모든것을 처리한다.
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public UserVo save(UserVo user) {
         em.persist(user);
        return user;
    }

    @Override
    public Optional<UserVo> findById(Long id) {
        UserVo user = em.find(UserVo.class, id);
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<UserVo> findByName(String name) {
        List<UserVo> result = em.createQuery("select m from Member m where m.name = :name", UserVo.class)
                .setParameter("name",name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<UserVo> findAll() {
        return em.createQuery("select m from Member m", UserVo.class).getResultList();
    }
}
