package pl.sda.sda_biuro_podrozy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.sda_biuro_podrozy.entities.ContinentEntity;
import pl.sda.sda_biuro_podrozy.entities.HotelEntity;
import pl.sda.sda_biuro_podrozy.repository.ContinentRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContinentService {
    @Autowired
    ContinentRepository continentRepository;
//TODO delete if not needed
    public List<String> getListOfContinentsString() {
        List<ContinentEntity> continentsEnt = continentRepository.findAll();
        List<String> continents = new ArrayList<>();
        for (ContinentEntity continent : continentsEnt) {
            continents.add(continent.getContinent());
        }
        return continents;
    }


    public List<ContinentEntity> getListOfContinents() {
        List<ContinentEntity> continents = continentRepository.findAll();
        return continents;
    }

    public ContinentEntity getContinentById(Integer continentId) {
        ContinentEntity continent = continentRepository.findById(continentId).orElseThrow(RuntimeException::new);
        return continent;

    }
}