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
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    private Map<String, String> name;

    @JsonIgnore
    @OneToMany(mappedBy = "region")
    private Set<District> districts = new HashSet<>();

    public Region() {
        this.name = new HashMap<>();
    }

    public void setName(String language, String name) {
        this.name.put(language, name);
    }

    public String getName(String language) {
        return this.name.get(language);
    }

}
