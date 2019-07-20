package pl.sda.sda_biuro_podrozy.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "continents")
@Getter
@Setter
public class ContinentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "continent_id")
    private int continentId;

    @Column(name = "continent")
    @NotNull
    private String continent;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "continentEntity")
    private Set<CountryEntity> countries=new HashSet<>();
    public void addCountry(CountryEntity countryEntity){
        if (countries == null) {
            countries = new HashSet<>();
        }
        countries.add(countryEntity);
    }
}
