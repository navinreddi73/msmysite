package in.mysite.hotel.repositories;

import in.mysite.hotel.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepo extends JpaRepository<Hotel, String> {
}
