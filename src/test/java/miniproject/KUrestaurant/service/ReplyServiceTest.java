package miniproject.KUrestaurant.service;

import miniproject.KUrestaurant.domain.Member;
import miniproject.KUrestaurant.domain.Reply;
import miniproject.KUrestaurant.domain.Restaurant;
import miniproject.KUrestaurant.repository.ReplyRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ReplyServiceTest {

    @Autowired private ReplyService replyService;
    @Autowired private ReplyRepository replyRepository;

    @Test
    public void 댓글등록() {

        Member member = new Member();
        Restaurant restaurant = new Restaurant();

        Reply reply = Reply.createReply(member, restaurant);
        reply.setStar(4);
        reply.setComment("맛있어요");

        Long id = replyService.saveReply(reply);

        assertEquals(member.getReplies().size(),1);
        assertEquals(restaurant.getReplies().size(),1);
        assertEquals(replyService.findOne(id),reply);
    }

    @Test
    public void 별점오류() {

        Member member = new Member();
        Restaurant restaurant = new Restaurant();

        Reply reply = Reply.createReply(member, restaurant);
        reply.setStar(6);
        reply.setComment("별점오류");

        assertThrows(IllegalArgumentException.class, () ->
                replyService.saveReply(reply));
    }

    @Test
    public void 댓글삭제() {
        Member member = new Member();
        Restaurant restaurant = new Restaurant();

        Reply reply = Reply.createReply(member, restaurant);
        reply.setStar(4);
        reply.setComment("맛있어요");

        replyService.saveReply(reply);
        replyService.removeReply(reply);

        assertEquals(replyService.findReplies().size(),0);
        assertEquals(member.getReplies().size(), 0);

    }
}