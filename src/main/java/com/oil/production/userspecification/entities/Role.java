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
@AllArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    private Map<String, String> name;

    @Column(name = "role_order")
    private Integer roleOrder;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Role() {
        this.name = new HashMap<>();
    }

    public void setName(String language, String name) {
        this.name.put(language, name);
    }

    public String getName(String language) {
        return this.name.get(language);
    }
}
