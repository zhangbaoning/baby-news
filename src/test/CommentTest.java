package test;

import controller.CommentController;
import entity.Comment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.CommentService;

import java.util.List;

/**
 * @Description
 * @Author zhangbaoning
 * @Date 2018/4/27
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class CommentTest {
    private CommentController commentController;
    @Autowired
    private CommentService commentService;

    @Test
    public void getALl() {

    }

    @Test
    public void getCommentsByArticleId() {
        List<Comment> commentList = commentService.selectCommentsById("e882634c5f3b445c");
        System.out.println(commentList.toString());
    }

}
