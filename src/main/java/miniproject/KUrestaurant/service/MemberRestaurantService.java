package miniproject.KUrestaurant.service;

import lombok.RequiredArgsConstructor;
import miniproject.KUrestaurant.domain.MemberRestaurant;
import miniproject.KUrestaurant.repository.MemberRestaurantRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberRestaurantService {

    private final MemberRestaurantRepository memberRestaurantRepository;

    @Transactional
    public Long pickRestaurant(MemberRestaurant memberRestaurant) {
        memberRestaurantRepository.save(memberRestaurant);

        return memberRestaurant.getId();
    }

    @Transactional
    public void unpickRestaurant(MemberRestaurant memberRestaurant) {

        MemberRestaurant.removeMemberRestaurant(memberRestaurant);
        memberRestaurantRepository.remove(memberRestaurant);
    }

    public MemberRestaurant findOne(Long memberRestaurantId) {
        return memberRestaurantRepository.findOne(memberRestaurantId);
    }
}
