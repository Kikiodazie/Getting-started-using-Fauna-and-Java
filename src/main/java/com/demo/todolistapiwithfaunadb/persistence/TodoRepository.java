package com.demo.todolistapiwithfaunadb.persistence;

import com.demo.todolistapiwithfaunadb.data.TodoEntity;
import org.springframework.stereotype.Repository;

@Repository
public class TodoRepository extends FaunaRepository<TodoEntity> {

    public TodoRepository(){
        super(TodoEntity.class, "todos", "all_todos");
    }

    //-- Custom repository operations specific to the TodoEntity will go below --//

}
