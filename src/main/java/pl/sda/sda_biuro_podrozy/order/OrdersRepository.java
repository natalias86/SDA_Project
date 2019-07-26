package pl.sda.sda_biuro_podrozy.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.sda_biuro_podrozy.entities.OrderEntity;
@Repository
public interface OrdersRepository extends JpaRepository<OrderEntity, Integer>{}
