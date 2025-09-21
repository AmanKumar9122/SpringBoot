package com.example.TodoApiSpring;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TodoController {
    private static List<Todo> todoList;

    public TodoController(){
        todoList  = new ArrayList<>();
        todoList.add(new Todo(1,false,"Todo 1", 1));
        todoList.add(new Todo(2,true,"Todo 2", 2));
    }

    @GetMapping("/todos")
    public ResponseEntity <List<Todo>> getTodos(){
        return ResponseEntity.status(HttpStatus.OK).body(todoList);
    }

    @PostMapping("/todos")
//    @ResponseStatus(HttpStatus.CREATED) instead of this we can use response entity class
    public ResponseEntity <Todo> createTodo(@RequestBody Todo newTodo){
        todoList.add(newTodo);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTodo);
    }

    @GetMapping("/todos/{todoId}")
    public ResponseEntity <Todo> getTodoById(@PathVariable int todoId){
        for(Todo todo : todoList){
            if(todo.getId()==todoId){
                return ResponseEntity.ok(todo);
            }
        }
        return ResponseEntity.notFound().build();
    }


}

