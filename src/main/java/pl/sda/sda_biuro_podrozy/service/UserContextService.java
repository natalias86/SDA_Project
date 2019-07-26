package pl.sda.sda_biuro_podrozy.service;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.annotation.SessionScope;
import pl.sda.sda_biuro_podrozy.cart.Cart;
import pl.sda.sda_biuro_podrozy.cart.ItemEntity;
import pl.sda.sda_biuro_podrozy.dto.PostDto;
import pl.sda.sda_biuro_podrozy.entities.PostEntity;
import pl.sda.sda_biuro_podrozy.entities.UserEntity;
import pl.sda.sda_biuro_podrozy.repository.UserRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
@SessionScope
public class UserContextService {
    @Autowired
    UserRepository userRepository;

    public String getLoggedUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            return null;
        }
        return authentication.getName();
    }

    public Optional<UserEntity> getUser() {
        return userRepository.findByEmail(getLoggedUserEmail());
    }

/*
    public boolean admin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getAuthorities().stream().anyMatch(e -> RoleTypeEnum.ADMIN.getRoleName().equalsIgnoreCase(e.getAuthority()))) {
            return true;
        }
        return false;
    }

    public boolean user() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getAuthorities().stream().anyMatch(e -> RoleTypeEnum.USER.getRoleName().equalsIgnoreCase(e.getAuthority()))) {
            return true;
        }
        return false;
    }*/


    @Getter
    private static Cart cart = new Cart();

    public void getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    }


}