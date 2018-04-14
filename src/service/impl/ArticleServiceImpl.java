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

    /**
     * 对资讯进行分页查询
     *
     * @param page 第几页
     * @param size 每一页的资讯数
     * @return
     */
    public List<Article> selectArticleAll(int page, int size) {
//        查询到所有的资讯
        List<Article> articleList = articleDao.selectAll();
//        分页开始List标号
        int articleListEnd = page * size - 1;
//        分页结束List标号
        int articleListStart = (page - 1) * size;
//        截取需要的分页资讯
        return articleList.subList(articleListStart, articleListEnd);
    }
}