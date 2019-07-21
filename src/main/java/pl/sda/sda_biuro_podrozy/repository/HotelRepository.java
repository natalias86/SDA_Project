package pl.sda.sda_biuro_podrozy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.sda_biuro_podrozy.entities.CityEntity;
import pl.sda.sda_biuro_podrozy.entities.HotelEntity;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface HotelRepository extends JpaRepository<HotelEntity, Integer> {
    List<HotelEntity> findByCityEntity(CityEntity cityEntity);
    Optional<HotelEntity> findAllByName(String name);

   // Optional<HotelEntity> findAllByCityEntity();
   // Optional<HotelEntity> findAllByStandard();
}
