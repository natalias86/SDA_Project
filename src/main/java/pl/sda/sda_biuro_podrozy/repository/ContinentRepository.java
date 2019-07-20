package pl.sda.sda_biuro_podrozy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.sda_biuro_podrozy.entities.ContinentEntity;

import java.util.Optional;

@Repository
public interface ContinentRepository  extends JpaRepository<ContinentEntity, Integer> {
    Optional<ContinentEntity> findByContinent (String name);
}
