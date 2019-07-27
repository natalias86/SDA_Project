package pl.sda.sda_biuro_podrozy.cart;

import lombok.Getter;
import lombok.Setter;
import org.assertj.core.util.Lists;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Setter
public class Cart {

    private BigDecimal price;
    private List<ItemEntity> itemList = Lists.newArrayList();

    public List<ItemEntity> getCartItems() {
        return itemList;
    }

    public boolean isCartEmpty() {
        return this.itemList.isEmpty();
    }

    public void clearCart() {
        this.itemList.removeAll(itemList);
    }
}





