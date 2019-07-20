package pl.sda.sda_biuro_podrozy.dto;

import lombok.Getter;
import lombok.Setter;
import pl.sda.sda_biuro_podrozy.entities.HotelEntity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;


@Getter
@Setter
public class PostDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="post_id")
    Integer postId;

    @Column(name="post_title")
    @NotNull(message="Enter title")
    @Size(max=50)
    private String postTitle;

    @Column(name="post_description")
    @NotNull(message="Enter post description")
    private String postDescription;

    @Column(name="price")
    @NotNull(message="Enter price")
    private BigDecimal price;

    @Column(name="vacancies")
    @NotNull(message="Enter limit of vacancies")
    private int vacancy;

    @Column
    @NotNull(message="Enter start day")
    private String startDate;

    @Column
    @NotNull(message="Enter end day")
    private String endDate;

    @Column
    @NotNull(message="Enter if BB, HB, FB, AI")
    private String type;

    @NotNull
    private HotelEntity hotelEntity;

    @Column(name ="is_promoted")
    private boolean promoted;
}
