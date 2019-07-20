package pl.sda.sda_biuro_podrozy.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Table(name = "users")
@Getter
@Setter
@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private int userId;

    @Column(name = "first_name", length = 100) //jesli nie okreslimy długości defaultowo będzie 255 znaków
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(length = 150, unique = true)
    private String email;

    @Column
    private String password;

    @Column
    private String role;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role")
    private Set<RoleEntity> roles;

    public void addRole(RoleEntity roleEntity) {
        if (roles == null) {
            roles = new HashSet<>();
        }
        roles.add(roleEntity);
    }

}
