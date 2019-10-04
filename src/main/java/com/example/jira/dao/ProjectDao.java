package com.example.jira.dao;
import com.example.jira.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectDao extends JpaRepository<Project, Integer> {

    Project findById(int id);

    List<Project> findAll();
/*
    @Query("SELECT id, nom, prix FROM Project p WHERE p.prix > :prixLimit")
    List<Project>  chercherUnProduitCher(@Param("prixLimit") int prix); */
}
