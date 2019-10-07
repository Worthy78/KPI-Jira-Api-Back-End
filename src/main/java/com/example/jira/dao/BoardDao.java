package com.example.jira.dao;

import com.example.jira.model.Board;
import com.example.jira.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardDao extends JpaRepository<Board, Integer> {

}
