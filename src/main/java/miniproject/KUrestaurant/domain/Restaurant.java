package miniproject.KUrestaurant.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Restaurant {

    @Id
    @GeneratedValue
    @Column(name = "restaurant_id")
    private Long id;

    private String name;
    private String phoneNumber;
    private Long star = 0L;
    private Long eval_num = 0L;

    @Lob
    private Blob thumbNail;

    private String address;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    @OneToMany(mappedBy = "restaurant")
    private List<MemberRestaurant> memberRestaurants = new ArrayList<>();

    @OneToMany(mappedBy = "restaurant")
    private List<Reply> replies = new ArrayList<>();


    //==비즈니스 로직==//
    /** 평균 별점 계산 */
    public float getAverageStar() {
        return getStar() / getEval_num();
    }

    /** 별점 추가 */
    public void addStar(Reply reply) {
        star = star + reply.getStar();
        eval_num++;
    }

    /** 별점 제거 */
    public void removeStar(Reply reply) {
        star = star - reply.getStar();
        eval_num--;
    }
}
