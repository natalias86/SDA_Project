package pl.sda.sda_biuro_podrozy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.sda_biuro_podrozy.entities.PostEntity;

import java.util.List;
import java.util.Optional;

@Repository
    public interface PostRepository extends JpaRepository<PostEntity, Integer> {
    @Override
    Optional<PostEntity> findById(Integer postId);

  /*  List<PostEntity> findByHotelEntity_Standard(Integer standard);
    List<PostEntity> findAllByHotelEntity ();*/

}