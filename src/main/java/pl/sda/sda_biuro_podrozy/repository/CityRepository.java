package pl.sda.sda_biuro_podrozy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.sda_biuro_podrozy.entities.CityEntity;
import pl.sda.sda_biuro_podrozy.entities.ContinentEntity;
import pl.sda.sda_biuro_podrozy.entities.CountryEntity;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<CityEntity, Integer> {
    //TODO optioanl
    List<CityEntity> findByCountryEntity(CountryEntity countryEntity);

}
