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
@ToString(exclude = {"boards"})
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Project implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    //@GeneratedValue(strategy = GenerationType.AUTO)
    //private  Long projectId;

    private String name ;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "project")
    private Set<Board> boards =  new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;
}
