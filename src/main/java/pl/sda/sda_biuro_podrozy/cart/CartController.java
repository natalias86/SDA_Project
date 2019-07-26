package pl.sda.sda_biuro_podrozy.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.sda.sda_biuro_podrozy.dto.PostDto;
import pl.sda.sda_biuro_podrozy.entities.PostEntity;
import pl.sda.sda_biuro_podrozy.entities.UserEntity;
import pl.sda.sda_biuro_podrozy.repository.PostRepository;
import pl.sda.sda_biuro_podrozy.service.PostService;
import pl.sda.sda_biuro_podrozy.service.UserContextService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@Controller
public class CartController {

    private PostRepository postRepository;
    private UserContextService userContextService;
    private PostService postService;
    private ItemRepository itemRepository;
    private CartService cartService;

    @Autowired
    public CartController(PostRepository postRepository, UserContextService userContextService, PostService postService, ItemRepository itemRepository, CartService cartService) {
        this.postRepository = postRepository;
        this.userContextService = userContextService;
        this.postService = postService;
        this.itemRepository = itemRepository;
        this.cartService = cartService;
    }

    @PostMapping("/cart/post/{postId}")
    public String buy(@PathVariable ("postId") Integer postId, @ModelAttribute("buyItem") @Valid ItemEntity itemEntity, BindingResult bindingResult, Model model) {
        PostEntity postEntity = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid post Id:" + postId));

        cartService.addPostToCart(new ItemEntity(1, postEntity));
        return "redirect:/cart";

    }

    @GetMapping("/cart")
    public String showCart(Model model) {

        model.addAttribute("totalPrice", cartService.calculateTotalPrice());
        model.addAttribute("cart", cartService.getCart());
        model.addAttribute("user", userContextService.getUser().orElse(new UserEntity()));

        return "cartElements";

    }
}


