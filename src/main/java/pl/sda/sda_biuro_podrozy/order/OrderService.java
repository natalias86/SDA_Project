package pl.sda.sda_biuro_podrozy.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.sda_biuro_podrozy.cart.Cart;
import pl.sda.sda_biuro_podrozy.cart.CartService;
import pl.sda.sda_biuro_podrozy.entities.CustomerEntity;
import pl.sda.sda_biuro_podrozy.entities.OrderEntity;
import pl.sda.sda_biuro_podrozy.entities.UserEntity;
import pl.sda.sda_biuro_podrozy.repository.PostRepository;
import pl.sda.sda_biuro_podrozy.repository.UserRepository;
import pl.sda.sda_biuro_podrozy.service.UserContextService;

@Service
public class OrderService {

    @Autowired
    private UserContextService userContextService;

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartService cartService;

    @Autowired
    private PostRepository postRepository;
/*
    public OrderEntity placeOrder() {
        Cart cart = userContextService.getCartElements();
        String loggedUserEmail = userContextService.getLoggedUserEmail();
        UserEntity userEntity = userRepository.findByEmail(loggedUserEmail);

        cart.stream()
                .peek(p -> p.getProduct().setStockAmount(p.getProduct().getStockAmount() - p.getQuantity()))
                .map(e -> e.getProduct()).forEach(productRepository::save);

        Order order = ordersRepository.save(new Order(customer.getUsername(), cartService.calculateTotalCartPrice(cart), customer.getUserAddress(), customer.getUserAddress(), LocalDateTime.now(), cart.getOrderLines(), customer, OrderStatus.NEW));
        userContextService.clearCart();
        return order;
}
}*/

}
