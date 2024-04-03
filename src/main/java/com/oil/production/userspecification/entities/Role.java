package com.oil.production.userspecification.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Entity
@Getter
@Setter
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameUz;
    private String nameEn;
    private String nameRu;

    @Column(name = "role_order")
    private Integer roleOrder;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;
}
