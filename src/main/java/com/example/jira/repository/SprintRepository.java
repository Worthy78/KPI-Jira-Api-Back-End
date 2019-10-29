package com.example.jira.repository;

import com.example.jira.domain.Sprint;

import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;


public interface SprintRepository extends JpaRepository<Sprint, Integer> {
    @Override
    Optional<Sprint> findById(Integer s);
    Page<Sprint> findByBoardId(Integer boardId, Pageable pageable);
    Page<Sprint> findByBoardIdAndState(Integer boardId,String state,Pageable pageable);
    Page<Sprint> findByEndDateGreaterThanEqualAndStartDateLessThanEqual(LocalDate aLocalDate,LocalDate bLocalDate,Pageable pageable);
    Page<Sprint> findByStateEquals(String state,Pageable pageable);
   // Page<Sprint> findByStartDateGreaterThanEqual(LocalDate localDate,Pageable pageable);
}
