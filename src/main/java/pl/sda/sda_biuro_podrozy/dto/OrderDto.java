package pl.sda.sda_biuro_podrozy.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.sda.sda_biuro_podrozy.cart.ItemEntity;
import pl.sda.sda_biuro_podrozy.entities.UserEntity;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private BigDecimal totalPrice;
    private UserEntity userEntity;
    private List<ItemEntity> itemEntity;
    LocalDate orderDate;

}
