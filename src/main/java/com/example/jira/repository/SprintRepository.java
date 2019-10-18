package com.example.jira.repository;

import com.example.jira.domain.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SprintRepository extends JpaRepository<Sprint, Integer> {
    @Override
    Optional<Sprint> findById(Integer s);
    List <Sprint> findByBoardId(Integer boardId);
}
