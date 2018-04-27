package test;

import entity.Article;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.ArticleService;

import javax.annotation.Resource;

/**
 * @Description
 * @Author zhangbaoning
 * @Date 2018/4/27
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})

public class ArticleServiceTest {
    @Resource
    private ArticleService articleService;

    @Test
    public void getArticle() {
        Article article = new Article();
        article.setId("28a2fcde348042db");
        article = articleService.selectArticle(article);
        System.out.println(article);
    }
}
