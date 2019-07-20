package pl.sda.sda_biuro_podrozy.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "countries")
@Getter
@Setter
public class CountryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_id")
    private int countryId;

    @Column(name = "country")
    @NotNull
    private String country;

    @ManyToOne
    @JoinColumn(name = "continent_id", nullable = false)
    private ContinentEntity continentEntity;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "countryEntity")
    private Set<CityEntity> city = new HashSet<>();

    public void addCity(CityEntity cityEntity) {
        if (city == null) {
            city = new HashSet<>();
        }
        city.add(cityEntity);
    }
}
