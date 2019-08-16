package pl.sda.sda_biuro_podrozy.cart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import pl.sda.sda_biuro_podrozy.entities.CityEntity;
import pl.sda.sda_biuro_podrozy.entities.OrderEntity;
import pl.sda.sda_biuro_podrozy.entities.PostEntity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Integer itemId;

    @Column
    @Min(value=1, message="incorrect number")
    private Integer numberOfTravelers;

    @OneToOne
    @JoinColumn(name = "post_id", nullable = false)
    private PostEntity postEntity;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity orderEntity;

    public ItemEntity(int numberOfTravelers) {
        this.numberOfTravelers = numberOfTravelers;
    }

    public ItemEntity(Integer numberOfTravelers, PostEntity postEntity) {
        this.numberOfTravelers = numberOfTravelers;
        this.postEntity = postEntity;
    }


}
