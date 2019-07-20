package pl.sda.sda_biuro_podrozy.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
@Getter
@Setter
@ToString(exclude = {"password"})
public class RegisterForm {
    @NotNull(message = "this field is mandatory")
    private String firstName;
    @NotBlank(message = "this field is mandatory")
    private String lastName;
    @Pattern(regexp="[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+\\.[a-z]{2,3}", message = "incorrect email")
    private String email;
    @Size(min=5,max=10)
    private String password;


}
