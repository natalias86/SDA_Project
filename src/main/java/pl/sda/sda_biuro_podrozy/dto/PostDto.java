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


    Integer postId;
    @NotNull(message="Enter title")
    @Size(max=50)
    private String postTitle;
    @NotNull(message="Enter post description")
    private String postDescription;
    @NotNull(message="Enter price")
    private BigDecimal price;
    @NotNull(message="Enter limit of vacancies")
    private int vacancy;
    @NotNull(message="Enter start day")
    private String startDate;
    @NotNull(message="Enter end day")
    private String endDate;
    @NotNull(message="Enter if BB, HB, FB, AI")
    private String type;
    @NotNull
    private HotelEntity hotelEntity;
    private boolean promoted;
}
