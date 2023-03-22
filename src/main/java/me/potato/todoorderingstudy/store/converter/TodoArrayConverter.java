package me.potato.todoorderingstudy.store.converter;

import com.fasterxml.jackson.core.type.TypeReference;
import me.potato.todoorderingstudy.store.entity.Todo;


public class TodoArrayConverter extends JsonArrayConverter<Todo>{
    public TodoArrayConverter() {
        super(new TypeReference<>() {
        });
    }
}
