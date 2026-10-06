package com.example.jdbc.controllers;

import com.example.jdbc.model.ToDo;
import com.example.jdbc.services.ToDoRepository;
import com.example.jdbc.services.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ToDoController {

    @Autowired
    ToDoRepository toDoService;

    @PostMapping("/todo")
    void save(@RequestBody ToDo todo){
        toDoService.save(todo);
    }

    @GetMapping("/todo")
        List<ToDo> findAll(){
            return toDoService.findAll();
        }

//    @DeleteMapping("/todo/{id}")
//    void delete(@PathVariable int id) {
//        toDoService.deleteById(id);
//    }

}
