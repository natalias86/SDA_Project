package pl.sda.sda_biuro_podrozy.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "post_entity")
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    Integer postId;

    @Column(name = "post_title")
    @NotNull(message = "Enter title")
    @Size(max = 50)
    private String postTitle;

    @Column(name = "post_description")
    @NotNull(message = "Enter post description")
    private String postDescription;

    @Column(name = "price")
    @NotNull(message = "Enter price")
    private BigDecimal price;

    @Column(name = "vacancies")
    @NotNull(message = "Enter limit of vacancies")
    private int vacancy;

    @Column(name = "start_date")
    @NotNull(message = "Enter start day")
    private LocalDate startDate;

    @Column(name = "end_date")
    @NotNull(message = "Enter end day")
    private LocalDate endDate;

    @Column(name = "type")
    @NotNull(message = "Enter if BB, HB, FB, AI")
    private String type;

    @Column(name = "is_promoted")
    private boolean promoted;

    @ManyToOne
    @JoinColumn(name = "hotel_id", nullable = false)
    private HotelEntity hotelEntity;

    public PostEntity() {
    }

    public PostEntity(@NotNull(message = "Enter title") @Size(max = 50) String postTitle, @NotNull(message = "Enter post description") String postDescription, @NotNull(message = "Enter price") BigDecimal price, @NotNull(message = "Enter limit of vacancies") int vacancy, @NotNull(message = "Enter start day") LocalDate startDate, @NotNull(message = "Enter end day") LocalDate endDate, @NotNull(message = "Enter if BB, HB, FB, AI") String type, boolean promoted, HotelEntity hotelEntity) {
        this.postTitle = postTitle;
        this.postDescription = postDescription;
        this.price = price;
        this.vacancy = vacancy;
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = type;
        this.promoted = promoted;
        this.hotelEntity = hotelEntity;
    }
}
