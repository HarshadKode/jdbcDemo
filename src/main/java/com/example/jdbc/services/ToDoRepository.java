package com.example.jdbc.services;

import com.example.jdbc.model.ToDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ToDoRepository {

    @Autowired
    DataSource dataSource;

    public void save(ToDo todo){
        String sql = "INSERT INTO todo (task) VALUES (?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql,
                     Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, todo.getTask());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Failed to save todo", ex);
        }
    }

    public List<ToDo> findAll() {
        String sql = "SELECT id, task FROM todo";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery();) {

            List<ToDo> todos = new ArrayList<>();
            while (rs.next()) {
                todos.add(mapRow(rs));
            }
            return todos;
        }
        catch(SQLException ex){
            throw new RuntimeException("Failed to load todos", ex);
        }
    }

    private ToDo mapRow(ResultSet rs)
            throws SQLException {
        return new ToDo(
                rs.getLong("id"),
                rs.getString("task")
        );
    }



}
