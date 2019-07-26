package pl.sda.sda_biuro_podrozy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.sda_biuro_podrozy.entities.CustomerEntity;
import pl.sda.sda_biuro_podrozy.entities.UserEntity;

//@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer>{

        boolean existsByUserEntity(UserEntity userEntity);
}
