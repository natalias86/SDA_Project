package pl.sda.sda_biuro_podrozy.entities;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import pl.sda.sda_biuro_podrozy.cart.ItemEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name="orders")
@NoArgsConstructor
@AllArgsConstructor
public class
OrderEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    Integer orderId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id", nullable = false)
    private ItemEntity itemEntity;
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
   UserEntity  userEntity;

}

