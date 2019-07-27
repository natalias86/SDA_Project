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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id", nullable = false)
    private ItemEntity itemEntity;
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    UserEntity userEntity;

}

