package miniproject.KUrestaurant.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Reply {

    @Id @GeneratedValue
    @Column(name="reply_id")
    private Long id;

    private int star;
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    //==생성 메서드==//
    public static Reply createReply(Member member, Restaurant restaurant) {
        Reply reply = new Reply();

        reply.setMember(member);
        member.getReplies().add(reply);
        reply.setRestaurant(restaurant);
        restaurant.getReplies().add(reply);
        restaurant.addStar(reply);

        return reply;
    }

    //==제거 메서드==//
    public static void removeReply(Reply reply) {
        Member member = reply.getMember();
        Restaurant restaurant = reply.getRestaurant();

        member.getReplies().remove(reply);
        restaurant.getReplies().remove(reply);
    }
}
