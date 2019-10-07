package com.example.jira.dao;

import com.example.jira.model.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SprintDao  extends JpaRepository<Sprint, Integer> {
}
