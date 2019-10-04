package com.example.jira.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Board   {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String type;

    @OneToMany(mappedBy = "board")
    private List<Sprint> sprint = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "PROJECT_ID")
    private Project project;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Board{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}