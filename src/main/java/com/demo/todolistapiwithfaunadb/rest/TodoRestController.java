package com.demo.todolistapiwithfaunadb.rest;

import com.demo.todolistapiwithfaunadb.data.CreateOrUpdateTodoData;
import com.demo.todolistapiwithfaunadb.data.TodoEntity;
import com.demo.todolistapiwithfaunadb.data.dataModel.Page;
import com.demo.todolistapiwithfaunadb.data.dataModel.PaginationOptions;
import com.demo.todolistapiwithfaunadb.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@RestController
public class TodoRestController {

    @Autowired
    private TodoService todoService;


    @PostMapping("/todos")
    public CompletableFuture<ResponseEntity> createTodo(@RequestBody CreateOrUpdateTodoData data) {

        return todoService.createTodo(data)
                .thenApply(todoEntity -> new ResponseEntity(todoEntity, HttpStatus.CREATED));
    }


    @GetMapping("/todos/{id}")
    public CompletableFuture<ResponseEntity> getTodo(@PathVariable("id") String id) {
        CompletableFuture<ResponseEntity> result =
                todoService.getTodo(id)
                        .thenApply(optionalTodoEntity ->
                                optionalTodoEntity
                                        .map(todoEntity -> new ResponseEntity(todoEntity, HttpStatus.OK))
                                        .orElseGet(() -> new ResponseEntity(HttpStatus.NOT_FOUND))
                        );
        return result;
    }


    @PutMapping("/todos/{id}")
    public CompletableFuture<ResponseEntity> updateTodo(@PathVariable("id") String id, @RequestBody CreateOrUpdateTodoData data) {
        CompletableFuture<ResponseEntity> result =
                todoService.updateTodo(id, data)
                        .thenApply(optionalTodoEntity ->
                                optionalTodoEntity
                                        .map(todoEntity -> new ResponseEntity(todoEntity, HttpStatus.OK))
                                        .orElseGet(() -> new ResponseEntity(HttpStatus.NOT_FOUND)
                                        )
                        );
        return result;
    }

    @DeleteMapping(value = "/todos/{id}")
    public CompletableFuture<ResponseEntity> deletePost(@PathVariable("id")String id) {
        CompletableFuture<ResponseEntity> result =
                todoService.deleteTodo(id)
                        .thenApply(optionalTodoEntity ->
                                optionalTodoEntity
                                        .map(todo -> new ResponseEntity(todo, HttpStatus.OK))
                                        .orElseGet(() -> new ResponseEntity(HttpStatus.NOT_FOUND)
                                        )
                        );
        return result;
    }

    @GetMapping("/todos")
    public CompletableFuture<Page<TodoEntity>> getAllTodos(
            @RequestParam("size") Optional<Integer> size,
            @RequestParam("before") Optional<String> before,
            @RequestParam("after") Optional<String> after) {
        PaginationOptions po = new PaginationOptions(size, before, after);
        CompletableFuture<Page<TodoEntity>> result = todoService.getAllTodos(po);
        return result;
    }
}
