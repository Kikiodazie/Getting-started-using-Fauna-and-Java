package com.demo.todolistapiwithfaunadb.service;

import com.demo.todolistapiwithfaunadb.data.CreateOrUpdateTodoData;
import com.demo.todolistapiwithfaunadb.data.TodoEntity;
import com.demo.todolistapiwithfaunadb.data.dataModel.Page;
import com.demo.todolistapiwithfaunadb.data.dataModel.PaginationOptions;
import com.demo.todolistapiwithfaunadb.persistence.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;


    public CompletableFuture<TodoEntity> createTodo(CreateOrUpdateTodoData data) {
        CompletableFuture<TodoEntity> result =
                todoRepository.nextId()
                        .thenApply(id -> new TodoEntity(id, data.getTitle(), data.getDescription()))
                        .thenCompose(todoEntity -> todoRepository.save(todoEntity));

        return result;
    }



    public CompletableFuture<Optional<TodoEntity>> getTodo(String id) {
        return todoRepository.find(id);
    }

    public CompletableFuture<Optional<TodoEntity>> updateTodo(String id, CreateOrUpdateTodoData data) {
        CompletableFuture<Optional<TodoEntity>> result =
                todoRepository.find(id)
                        .thenCompose(optionalTodoEntity ->
                                optionalTodoEntity
                                        .map(todoEntity -> todoRepository.save(new TodoEntity(id, data.getTitle(), data.getDescription())).thenApply(Optional::of))
                                        .orElseGet(() -> CompletableFuture.completedFuture(Optional.empty())));

        return result;
    }



    public CompletableFuture<Optional<TodoEntity>> deleteTodo(String id) {
        return todoRepository.remove(id);
    }


    public CompletableFuture<Page<TodoEntity>> getAllTodos(PaginationOptions po) {
        return todoRepository.findAll(po);
    }


}
