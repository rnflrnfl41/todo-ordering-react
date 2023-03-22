package me.potato.todoorderingstudy.service;

import lombok.RequiredArgsConstructor;
import me.potato.todoorderingstudy.service.exceptions.OrderingNotExistException;
import me.potato.todoorderingstudy.store.entity.Ordering;
import me.potato.todoorderingstudy.store.entity.OrderingRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderingService {

    private final OrderingRepository repository;

    public Ordering getByUserNo(Long userNo) {

        return repository.findByUserNo(userNo)
                .orElseThrow(() -> new OrderingNotExistException("10005", "Ordering not exist.."));
    }
}
