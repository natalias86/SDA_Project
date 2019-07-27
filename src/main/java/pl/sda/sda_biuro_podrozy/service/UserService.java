package pl.sda.sda_biuro_podrozy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.sda.sda_biuro_podrozy.dto.RegisterForm;
import pl.sda.sda_biuro_podrozy.entities.RoleEntity;
import pl.sda.sda_biuro_podrozy.entities.UserEntity;
import pl.sda.sda_biuro_podrozy.repository.RoleRepository;
import pl.sda.sda_biuro_podrozy.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    public static final String ROLE_USER = "ROLE_USER";
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(RegisterForm registerForm) throws UserAlreadyExistException {

        if (userRepository.existsByEmail(registerForm.getEmail())) {
            throw new UserAlreadyExistException("Duplicate email");
        }

        UserEntity user = new UserEntity();
        user.setFirstName(registerForm.getFirstName());
        user.setLastName(registerForm.getLastName());
        user.setEmail(registerForm.getEmail());
        user.setPassword(passwordEncoder.encode(registerForm.getPassword()));  //  uzywamy passwordEncoder.encode zeby zapisywać hasło hashem, zakodowane, a nie plain textem-> BCrypt  szyfrujac hasło dodaje sól i hash hasła
        user.setRole("USER");

        userRepository.save(user);

    }

    public Optional<UserEntity> getUserById(Integer userId) {
        return userRepository.findById(userId);
    }

/*    public Optional<UserEntity> getUserByEmail(){

        return userRepository.findFirstByEmail(getLoggedUserEmail());

    }*/
}
