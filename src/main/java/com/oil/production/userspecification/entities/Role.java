package com.oil.production.userspecification.entities;

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
    @MapKeyColumn(name="language")
    @Column(name="name")
    @CollectionTable(name = "role_name_mapping",
            joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private Map<String, String> name;

    @Column(name = "role_order")
    private Integer roleOrder;

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
