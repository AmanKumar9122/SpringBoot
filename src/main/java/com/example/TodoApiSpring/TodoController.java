package com.example.TodoApiSpring;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/todos")
public class TodoController {
    // @Autowired

    private TodoService todoService; //composition(instance of other class as our class property)

    private TodoService todoService2;

    private static List<Todo> todoList;
    private static final String TODO_NOT_FOUND = "Todo not found";

    public TodoController(
            @Qualifier("anotherTodoService") TodoService todoService,
            @Qualifier("fakeTodoService") TodoService todoService2){

        this.todoService = todoService;
        this.todoService2 = todoService2;
        todoList  = new ArrayList<>();
        todoList.add(new Todo(1,false,"Todo 1", 1));
        todoList.add(new Todo(2,true,"Todo 2", 2));
        // this.todoService = new TodoService(); no instance attached to it
    }

    @GetMapping
    // query params
    public ResponseEntity <List<Todo>> getTodos(@RequestParam(required = false, defaultValue = "true") boolean isCompleted){
        // if we don't pass query params then 400 Bad Request will come.
        System.out.println("Incoming query param " + isCompleted + " " + this.todoService2.doSomething());
        return ResponseEntity.status(HttpStatus.OK).body(todoList);
    }

    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED) instead of this we can use response entity class
    public ResponseEntity <Todo> createTodo(@RequestBody Todo newTodo){
        todoList.add(newTodo);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTodo);
    }

    @GetMapping("{todoId}")
    // body params
    public ResponseEntity <?> getTodoById(@PathVariable int todoId){
        // in java {?} is a wildcard (anything can come over there)
        for(Todo todo : todoList){
            if(todo.getId()==todoId){
                return ResponseEntity.ok(todo);
            }
        }
        //return ResponseEntity.notFound().build();
        // along with 404 status code, try sending a json {message: JSON NOT FOUND};
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(TODO_NOT_FOUND);
    }
}

