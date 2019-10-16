package com.example.jira.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@ToString(exclude = {"projects"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    private String description;

    private String name;

   // @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "projectCategory")
    //@JsonManagedReference
    private Set<Project> projects =  new HashSet<>();
}
