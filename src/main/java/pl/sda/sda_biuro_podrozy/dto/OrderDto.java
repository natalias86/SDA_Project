package pl.sda.sda_biuro_podrozy.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.sda.sda_biuro_podrozy.cart.ItemEntity;
import pl.sda.sda_biuro_podrozy.entities.PostEntity;
import pl.sda.sda_biuro_podrozy.entities.UserEntity;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    BigDecimal totalPrice;
  //  ItemEntity items;
    UserEntity userEntity;

}
