package pl.sda.sda_biuro_podrozy.entities;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cities")
@Getter
@Setter
public class CityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id")
    private int cityId;
    
    @Column(name = "city")
    @NotNull
    private String city;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private CountryEntity countryEntity;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "cityEntity")
    private Set<HotelEntity> hotels = new HashSet<>();
    public void addHotel(HotelEntity hotelEntity){
        if (hotels == null) {
            hotels = new HashSet<>();
        }
        hotels.add(hotelEntity);
    }
}
