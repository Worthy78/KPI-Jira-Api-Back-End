package com.example.jira.repository;

import com.example.jira.domain.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SprintRepository extends JpaRepository<Sprint, Integer> {
}
