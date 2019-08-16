package pl.sda.sda_biuro_podrozy.service;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import pl.sda.sda_biuro_podrozy.cart.Cart;
import pl.sda.sda_biuro_podrozy.cart.ItemEntity;
import pl.sda.sda_biuro_podrozy.controllers.CartController;
import pl.sda.sda_biuro_podrozy.entities.PostEntity;
import pl.sda.sda_biuro_podrozy.repository.ItemRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@Service

@SessionScope
public class CartService {

    @Autowired
    ItemRepository itemRepository;
    @Getter
    private Cart cart = new Cart();

    public void addPostToCart(ItemEntity newItem) {
        List<ItemEntity> items = cart.getCartItems();
        Optional<ItemEntity> first = items.stream().filter(p -> p.getPostEntity().equals(newItem.getPostEntity())).findFirst();
        if (first.isPresent()) {//TODO Validation message
            System.out.println("product already added");
        } else {
            ItemEntity item = new ItemEntity();
            item.setItemId(items.size() + 1);
            item.setPostEntity(newItem.getPostEntity());
            item.setNumberOfTravelers(1);
            items.add(item);
        }
    }

    public void updateNumberOfTravelers(int numOfTravelers, int itemId) {
        //logika

        ItemEntity item =  cart.getCartItems().get(itemId-1);
        item.setNumberOfTravelers(numOfTravelers);

    }

    public void removeItemFromCart(Integer itemId) {
        List<ItemEntity> items = cart.getCartItems();
        items.remove(itemId - 1);
    }


    public List<ItemEntity> getCartElements() {
        return cart.getCartItems();

    }

    public BigDecimal calculateTotalPrice() {

        return this.cart.getCartItems()
                .stream()
                .map(p -> p.getPostEntity().getPrice().multiply(new BigDecimal(p.getNumberOfTravelers())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void clearCart() {
        this.cart.clearCart();
    }


}
