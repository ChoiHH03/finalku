package miniproject.KUrestaurant.repository;

import lombok.RequiredArgsConstructor;
import miniproject.KUrestaurant.domain.MemberRestaurant;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class MemberRestaurantRepository {

    private final EntityManager em;

    public void save(MemberRestaurant memberRestaurant) {
        em.persist(memberRestaurant);
    }

    public void remove(MemberRestaurant memberRestaurant) {
        em.remove(memberRestaurant);
    }

    public MemberRestaurant findOne(Long memberRestaurantId) {
        return em.find(MemberRestaurant.class, memberRestaurantId);
    }
}
