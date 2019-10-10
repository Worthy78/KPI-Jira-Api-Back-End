package com.example.jira.domain;

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

    @Id
    private Long id;

    private String description;

    private String name;

   // @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "projectCategory")
    private Set<Project> projects =  new HashSet<>();
}
