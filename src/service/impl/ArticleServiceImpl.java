package service.impl;

import dao.ArticleDao;
import entity.Article;
import org.springframework.stereotype.Service;
import service.ArticleService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description
 * @Author zhangbaoning
 * @Date 2018/4/10
 */
@Service("articleService")
public class ArticleServiceImpl implements ArticleService {

    /**
     * 保存文章
     *
     * @param article
     */
    @Resource
    private ArticleDao articleDao;

    public void insertArticle(Article article) {
        articleDao.insert(article);
    }

    public void deleteArticle(Article article) {
        articleDao.delete(article);
    }

    public void updateArticle(Article article) {
        articleDao.updateByPrimaryKey(article);
    }

    public Article selectArticle(Article article){
        List<Article> articleList =articleDao.select(article);
        return articleList.get(0);
    }
}
