package com.oil.production.userspecification.entities;

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
    @MapKeyColumn(name="language")
    @Column(name="name")
    @CollectionTable(name = "district_name_mapping",
            joinColumns = {@JoinColumn(name = "district_id", referencedColumnName = "id")})
    private Map<String, String> name;

    @Column(name = "district_order")
    private Integer disOrder;

    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;

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
