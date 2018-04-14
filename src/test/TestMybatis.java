package test;

import entity.Article;
import entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.ArticleService;
import service.UserService;

import javax.annotation.Resource;

/**
 * @Description
 * @Author zhangbaoning
 * @Date 2018/4/11
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class TestMybatis {
    @Resource
    private ArticleService articleService;
    @Resource
    private UserService userService;

    @Test
    public void test1() {
        // System.out.println(user.getUserName());
        // logger.info("值："+user.getUserName());
        Article article = new Article();
        article.setId(3);
        article.setTitle("baoning");
//        article.setCategoryId("2");
//        article.setClickCount("2");
//        article.setContent("334");
//        article.setDesc("rretre");
        articleService.insertArticle(article);
//        articleService.selectArticle(article);
    }

    @Test
    public void testGetUser() {
        User user = new User();
        user.setId(1);
        System.out.println(userService.selectUser(user));

    }
}
