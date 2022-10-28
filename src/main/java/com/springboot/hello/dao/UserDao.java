package com.springboot.hello.dao;

import com.springboot.hello.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.*;

public class UserDao {

    private JdbcTemplate jdbcTemplate;

    public UserDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    RowMapper<User> rowMapper = new RowMapper<User>() {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User(rs.getString("id"), rs.getString("name"), rs.getString("password"));
            return user;
        }
    };

    public void add(User user) {
        this.jdbcTemplate.update("INSERT INTO users(id, name, password) values(?,?,?)", user.getId(), user.getName(), user.getPassword());
    }

    public User findById(String id) throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM `likelion-db`.users WHERE id = ?";

        return this.jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    public void deleteAll() {
        this.jdbcTemplate.update("DELETE FROM `likelion-db`.users");
    }

}
