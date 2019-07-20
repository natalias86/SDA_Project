package pl.sda.sda_biuro_podrozy.dto;

import lombok.Getter;
import lombok.Setter;
import pl.sda.sda_biuro_podrozy.entities.CountryEntity;

@Getter
@Setter
public class CityDto {
    private String city;
    private CountryEntity countryEntity;
}
