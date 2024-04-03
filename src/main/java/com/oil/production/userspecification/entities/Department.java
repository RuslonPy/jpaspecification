package com.oil.production.userspecification.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    private Map<String, String> name;

    @Column(name = "department_order")
    private Integer depOrder;

    @ManyToOne
    @JoinColumn(name = "parent_department_id")
    private Department parentDepartment;

    @JsonIgnore
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<User> users = new HashSet<>();

    public Department() {
        this.name = new HashMap<>();
    }

    public void setName(String language, String name) {
        this.name.put(language, name);
    }
    public String getName(String language) {
        return this.name.get(language);
    }

}
