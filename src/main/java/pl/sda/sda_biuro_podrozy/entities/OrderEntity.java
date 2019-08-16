package pl.sda.sda_biuro_podrozy.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.sda.sda_biuro_podrozy.cart.ItemEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class
OrderEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    Integer orderId;
    @Column(name = "total_price")
    private BigDecimal totalPrice;
    @Column(name = "order_date")
    private LocalDate orderDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orderEntity")
    private Set<ItemEntity> items = new HashSet<>();

    public void addItem(ItemEntity itemEntity) {
        if (items == null) {
            items = new HashSet<>();
        }
        items.add(itemEntity);
        itemEntity.setOrderEntity(this);
    }

    @ManyToOne
    @JoinColumn(name = "userId")
    UserEntity userEntity;

}

