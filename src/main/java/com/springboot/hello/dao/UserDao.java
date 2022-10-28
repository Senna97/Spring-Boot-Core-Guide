package com.springboot.hello.dao;

import com.springboot.hello.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

@Component
public class UserDao {

    private final DataSource dataSource;
    private final JdbcTemplate jdbcTemplate;

    public UserDao(DataSource dataSource, JdbcTemplate jdbcTemplate) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    public void add(User user) {
        this.jdbcTemplate.update("INSERT INTO users(id, name, password) values(?,?,?)", user.getId(), user.getName(), user.getPassword());
    }

    public int deleteAll() {
        return this.jdbcTemplate.update("DELETE FROM `likelion-db`.users");
    }

    public User findById(String id) {
        Map<String, String> env = System.getenv();
        Connection c;
        try {
            c = dataSource.getConnection();

            PreparedStatement ps = c.prepareStatement("SELECT * FROM `likelion-db`.users WHERE id = ?");
            ps.setString(1, id);

            ResultSet rs = ps.executeQuery();
            User user = null;
            if (rs.next()) {
                user = new User(rs.getString("id"), rs.getString("name"), rs.getString("password"));
            }

            rs.close();
            ps.close();
            c.close();

            if (user == null) throw new RuntimeException();

            return user;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
