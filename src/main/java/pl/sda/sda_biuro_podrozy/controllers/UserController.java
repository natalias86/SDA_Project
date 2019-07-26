package pl.sda.sda_biuro_podrozy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.sda.sda_biuro_podrozy.dto.RegisterForm;
import pl.sda.sda_biuro_podrozy.service.UserAlreadyExistException;
import pl.sda.sda_biuro_podrozy.service.UserService;

import javax.validation.Valid;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("registerForm", new RegisterForm());
        return "registrationForm";
    }

    @PostMapping("/register")
    public String handleRegisterForm(
            @ModelAttribute @Valid
                    RegisterForm registerForm,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {

            return "registrationForm";
        }
        try {
            userService.registerUser(registerForm);
        } catch (UserAlreadyExistException e) {
            bindingResult.rejectValue("email", "email-duplicate", "duplikat emaila");
            return "registrationForm";
        }
        redirectAttributes.addFlashAttribute("msg", "thank you for registration");
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "loginForm";
    }

}
