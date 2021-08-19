package miniproject.KUrestaurant.service;

import miniproject.KUrestaurant.domain.Member;
import miniproject.KUrestaurant.domain.MemberRestaurant;
import miniproject.KUrestaurant.domain.Restaurant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberRestaurantServiceTest {

    @Autowired MemberRestaurantService memberRestaurantService;

    @Test
    public void 레스토랑픽() {

        Member member = new Member();
        Restaurant restaurant = new Restaurant();
        MemberRestaurant memberRestaurant = MemberRestaurant.pickMemberRestaurant(member, restaurant);

        Long id = memberRestaurantService.pickRestaurant(memberRestaurant);

        assertEquals(memberRestaurantService.findOne(id),memberRestaurant);
        assertEquals(member.getMemberRestaurants().size(),1);
    }

    @Test
    public void 레스토랑언픽() {

        Member member = new Member();
        Restaurant restaurant = new Restaurant();
        MemberRestaurant memberRestaurant = MemberRestaurant.pickMemberRestaurant(member, restaurant);

        Long id = memberRestaurantService.pickRestaurant(memberRestaurant);
        memberRestaurantService.unpickRestaurant(memberRestaurant);

        assertEquals(member.getMemberRestaurants().size(), 0);
    }
}