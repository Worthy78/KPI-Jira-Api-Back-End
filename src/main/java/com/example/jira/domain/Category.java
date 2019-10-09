package com.example.jira.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@ToString(exclude = {"boards"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category implements Serializable {

    @Id
    private Long id;

    private String description;

    private String name;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    private Set<Project> projects =  new HashSet<>();
}
