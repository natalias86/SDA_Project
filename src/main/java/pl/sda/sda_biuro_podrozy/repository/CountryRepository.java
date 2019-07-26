package pl.sda.sda_biuro_podrozy.repository;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.sda_biuro_podrozy.entities.ContinentEntity;
import pl.sda.sda_biuro_podrozy.entities.CountryEntity;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<CountryEntity, Integer> {

    //TODO optional
    List<CountryEntity> findByContinentEntity(ContinentEntity continentEntity);
}
