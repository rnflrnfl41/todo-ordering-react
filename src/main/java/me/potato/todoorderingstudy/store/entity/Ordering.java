package me.potato.todoorderingstudy.store.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.potato.todoorderingstudy.store.converter.TodoArrayConverter;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Ordering {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private Long userNo;

    @Column(columnDefinition = "json")
    @Convert(converter = TodoArrayConverter.class)
    private List<Todo> todos;




}
