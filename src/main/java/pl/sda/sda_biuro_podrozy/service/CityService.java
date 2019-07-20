package pl.sda.sda_biuro_podrozy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.sda_biuro_podrozy.dto.CityDto;
import pl.sda.sda_biuro_podrozy.dto.CountryDto;
import pl.sda.sda_biuro_podrozy.entities.CityEntity;
import pl.sda.sda_biuro_podrozy.entities.ContinentEntity;
import pl.sda.sda_biuro_podrozy.entities.CountryEntity;
import pl.sda.sda_biuro_podrozy.repository.CityRepository;
import pl.sda.sda_biuro_podrozy.repository.ContinentRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CityService {
    @Autowired
    CityRepository cityRepository;

    public List<CityEntity> getListOfCities() {
        List<CityEntity> cities = cityRepository.findAll();
        return cities;
    }

    public void addCity(CityDto cityDto) {
        CityEntity cityEntity = new CityEntity();
        cityEntity.setCity(cityDto.getCity());
        cityEntity.setCountryEntity(cityDto.getCountryEntity());
        cityRepository.save(cityEntity);
    }
}
