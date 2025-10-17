package in.mysite.user.service.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;


public class Hotel {
    //@Id
    //@Column(length = 100)
    private String hotelId;
    //@Column(length = 25)
    private String name;
    //@Column(length = 100)
    private String location;
    //@Column(length = 100)
    private String about;
}
