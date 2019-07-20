package pl.sda.sda_biuro_podrozy.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "hotels")
@Getter
@Setter
public class HotelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hotel_id")
    private int hotelId;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "standard")
    @Size(min=1,max=5)
    private int standard;

    @Column(name = "description")
    @NotNull
    private String description;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private CityEntity cityEntity;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "hotelEntity")
    private Set<PostEntity> posts= new HashSet<>();

    public void addPost(PostEntity postEntity){
        if (posts == null) {
            posts = new HashSet<>();
        }
        posts.add(postEntity);
    }


}
