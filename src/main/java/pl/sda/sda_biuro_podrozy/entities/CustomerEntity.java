package pl.sda.sda_biuro_podrozy.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name="customer_entity")
@NoArgsConstructor
@AllArgsConstructor
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Integer customerId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;


    @Column(name = "phone")
    private Integer phone;

    @Column(name = "id_number")
    private String idNumber;

    @Column(name = "city")
    private String city;
/*
    @OneToMany(mappedBy = "customerEntity", cascade = CascadeType.ALL)
    private List<OrderEntity> ordersList = new ArrayList<>();*/
}