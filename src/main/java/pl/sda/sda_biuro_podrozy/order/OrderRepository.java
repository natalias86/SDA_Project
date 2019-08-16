package pl.sda.sda_biuro_podrozy.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.sda_biuro_podrozy.entities.OrderEntity;
@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {
}
