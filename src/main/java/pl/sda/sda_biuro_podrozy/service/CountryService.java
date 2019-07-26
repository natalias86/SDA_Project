package pl.sda.sda_biuro_podrozy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.sda_biuro_podrozy.dto.CountryDto;
import pl.sda.sda_biuro_podrozy.entities.ContinentEntity;
import pl.sda.sda_biuro_podrozy.entities.CountryEntity;
import pl.sda.sda_biuro_podrozy.repository.CountryRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CountryService {
    @Autowired
    CountryRepository countryRepository;


    public List<CountryEntity> getListOfCountries() {
        List<CountryEntity> countries = countryRepository.findAll();
        return countries;
    }

    public void addCountry(CountryDto countryDto) {
        CountryEntity countryEntity = new CountryEntity();
        countryEntity.setCountry(countryDto.getCountry());
        countryEntity.setContinentEntity(countryDto.getContinentEntity());
        countryRepository.save(countryEntity);
    }

    public List<CountryEntity> getListOfCountriesByContinent(ContinentEntity continentEntity) {
        List<CountryEntity> countriesByContinent = countryRepository.findByContinentEntity(continentEntity);
        return countriesByContinent;
    }

    public CountryEntity getCountryById(Integer countryId) {
        CountryEntity country = countryRepository.findById(countryId).orElseThrow(RuntimeException::new);
        return country;
    }
}
