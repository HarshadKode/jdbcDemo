package com.example.jdbc.services;

import com.example.jdbc.model.ToDo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ToDoService {

    List<ToDo> todoList = new ArrayList();

    public void save(ToDo todo) {
        todoList.add(todo);
    }

    public List<ToDo> findAll() {
        return todoList;
    }

    public void deleteById(int id){
        todoList.removeIf(toDo -> toDo.getId()== id);
    }

    public void update(ToDo todo) {

    }

}
