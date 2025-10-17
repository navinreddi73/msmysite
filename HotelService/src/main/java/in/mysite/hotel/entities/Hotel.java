package in.mysite.hotel.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table
public class Hotel {
    @Id
    @Column(length = 50)
    private String hotelId;
    @Column(length = 20)
    private String hotelName;
    @Column(length = 50)
    private String hotelAddress;
    @Column(length = 100)
    private String about;
}
