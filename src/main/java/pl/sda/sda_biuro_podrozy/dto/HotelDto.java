package pl.sda.sda_biuro_podrozy.dto;

import lombok.Getter;
import lombok.Setter;
import pl.sda.sda_biuro_podrozy.entities.CityEntity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class HotelDto {

    private int hotelId;

    @NotNull
    private String name;

    private String standard;

    @NotNull
    private String description;

    @NotNull
    private CityEntity cityEntity;


}
