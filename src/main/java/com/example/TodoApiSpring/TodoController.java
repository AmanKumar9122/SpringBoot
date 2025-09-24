package com.example.TodoApiSpring;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/todos")
public class TodoController {
    private static List<Todo> todoList;
    private static final String TODO_NOT_FOUND = "Todo not found";

    public TodoController(){
        todoList  = new ArrayList<>();
        todoList.add(new Todo(1,false,"Todo 1", 1));
        todoList.add(new Todo(2,true,"Todo 2", 2));
    }

    @GetMapping
    public ResponseEntity <List<Todo>> getTodos(){
        return ResponseEntity.status(HttpStatus.OK).body(todoList);
    }

    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED) instead of this we can use response entity class
    public ResponseEntity <Todo> createTodo(@RequestBody Todo newTodo){
        todoList.add(newTodo);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTodo);
    }

    @GetMapping("{todoId}")
    public ResponseEntity <?> getTodoById(@PathVariable int todoId){
        // in java {?} is a wildcard (anything can come over there
        for(Todo todo : todoList){
            if(todo.getId()==todoId){
                return ResponseEntity.ok(todo);
            }
        }
        //return ResponseEntity.notFound().build();
        // along with 404 status code, try send a json {message: JSON NOT FOUND};
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(TODO_NOT_FOUND);
    }
}

