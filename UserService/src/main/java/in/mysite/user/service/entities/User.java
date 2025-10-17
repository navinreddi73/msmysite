package in.mysite.user.service.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table
public class User {
    @Id
    @Column(length = 50)
    private String userId;
    @Column(length = 25)
    private String name;
    @Column(length = 255)
    private String email;
    @Column(length = 255)
    private String password;

    @Transient
    private List<Ratting> ratings = new ArrayList<Ratting>();


}
