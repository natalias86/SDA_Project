package pl.sda.sda_biuro_podrozy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.sda_biuro_podrozy.entities.HotelEntity;

import java.util.Optional;

@Repository
public interface HotelRepository extends JpaRepository<HotelEntity, Integer> {

    Optional<HotelEntity> findAllByName(String name);
}
