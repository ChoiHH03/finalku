package miniproject.KUrestaurant.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class MemberRestaurantRepository {

    private final EntityManager em;

    
}
