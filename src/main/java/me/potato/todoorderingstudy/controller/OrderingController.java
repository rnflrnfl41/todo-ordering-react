package me.potato.todoorderingstudy.controller;

import lombok.RequiredArgsConstructor;
import me.potato.todoorderingstudy.service.OrderingService;
import me.potato.todoorderingstudy.store.entity.Ordering;
import me.potato.todoorderingstudy.store.entity.OrderingRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("orderings")
public class OrderingController {

    private final OrderingService service;

    @GetMapping("/list/{userNo}")
    public Ordering getOrderingByUserNo(@PathVariable Long userNo) {
        return service.getByUserNo(userNo);
    }
}
