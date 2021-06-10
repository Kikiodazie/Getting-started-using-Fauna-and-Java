package com.demo.todolistapiwithfaunadb.persistence;

import com.demo.todolistapiwithfaunadb.data.dataModel.Entity;
import com.demo.todolistapiwithfaunadb.data.dataModel.Page;
import com.demo.todolistapiwithfaunadb.data.dataModel.PaginationOptions;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface Repository<T extends Entity> {

    CompletableFuture<T> save(T entity);

    CompletableFuture<Optional<T>> find(String id);

    CompletableFuture<Page<T>> findAll(PaginationOptions po);

    CompletableFuture<Optional<T>> remove(String id);
}
