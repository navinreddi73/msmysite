package in.mysite.user.service.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table
public class Ratting {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 100)
    private String ratingId;
    @Column(length = 100)
    private String userId;
    @Column(length = 100)
    private String hotelId;
    @Column(length = 1)
    private String rating;
    @Column(length = 100)
    private String feedback;

    @Transient
    private Hotel hotel;
}
