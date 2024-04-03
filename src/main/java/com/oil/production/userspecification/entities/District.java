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
public class District {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    private Map<String, String> name;

    @Column(name = "district_order")
    private Integer disOrder;

    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;

    @JsonIgnore
    @OneToMany(mappedBy = "district", fetch = FetchType.EAGER)
    private Set<Address> addresses = new HashSet<>();

    public District() {
        this.name = new HashMap<>();
    }

    public void setName(String language, String name) {
        this.name.put(language, name);
    }

    public String getName(String language) {
        return this.name.get(language);
    }

}
