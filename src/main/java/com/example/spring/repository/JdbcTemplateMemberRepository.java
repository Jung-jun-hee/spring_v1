package com.example.spring.repository;

import com.example.spring.model.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.security.Policy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JdbcTemplateMemberRepository implements MemberRepository{

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTemplateMemberRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public UserVo save(UserVo user) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        user.setId(key.longValue());
        return user;
    }

    @Override
    public Optional<UserVo> findById(Long id) {
         List<UserVo> result = jdbcTemplate.query("select * from member where id = ?", memberRowMapper(),id );
        return result.stream().findAny();
    }

    @Override
    public Optional<UserVo> findByName(String name) {
        List<UserVo> result = jdbcTemplate.query("select * from member where name = ?", memberRowMapper(),name );
        return result.stream().findAny();
    }

    @Override
    public List<UserVo> findAll() {
        return jdbcTemplate.query("select * from member",memberRowMapper());
    }

    private RowMapper<UserVo> memberRowMapper(){
        return (rs, rowNum) -> {
            UserVo user = new UserVo();
            user.setId(rs.getLong("id"));
            user.setName(rs.getString("name"));
            return user;
        };
    }
}
