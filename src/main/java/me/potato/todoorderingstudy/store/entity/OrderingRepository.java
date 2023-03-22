package me.potato.todoorderingstudy.store.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderingRepository extends JpaRepository<Ordering,Long> {
    Optional<Ordering> findByUserNo(Long userNo);

    Ordering getByUserNo(Long userNo);
}
