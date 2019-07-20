package pl.sda.sda_biuro_podrozy.dto;

import lombok.Getter;
import lombok.Setter;
import pl.sda.sda_biuro_podrozy.entities.ContinentEntity;

@Getter
@Setter
public class CountryDto {
    private String country;
    private ContinentEntity continentEntity;
}
