package me.potato.todoorderingstudy.controller;

import lombok.RequiredArgsConstructor;
import me.potato.todoorderingstudy.controller.dto.CreateTodoRequest;
import me.potato.todoorderingstudy.service.SearchFlow;
import me.potato.todoorderingstudy.store.entity.Ordering;
import me.potato.todoorderingstudy.store.entity.Todo;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("searchFlows")
public class SearchFlowController {

    private final SearchFlow searchFlow;

    @PostMapping
    public Ordering create(@RequestBody CreateTodoRequest request) {
        return searchFlow.createSearchFlow(request);
    }

    @PutMapping("{id}")
    public Ordering complete(@PathVariable Long id) {
        return searchFlow.completeSearchFlow(id);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        searchFlow.delete(id);
    }

}
