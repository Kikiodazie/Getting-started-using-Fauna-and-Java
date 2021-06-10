package com.demo.todolistapiwithfaunadb.data;

import com.demo.todolistapiwithfaunadb.data.dataModel.Entity;
import com.faunadb.client.types.FaunaConstructor;
import com.faunadb.client.types.FaunaField;

public class TodoEntity extends Entity {

    @FaunaField
    private String title;

    @FaunaField
    private String description;

    @FaunaConstructor
    public TodoEntity(@FaunaField("id") String id,
                      @FaunaField("title") String title,
                      @FaunaField("description") String description) {
        this.id = id;
        this.title = title;
        this.description = description;

    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
