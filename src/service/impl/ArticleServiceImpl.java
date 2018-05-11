package service.impl;

import dao.ArticleDao;
import entity.Article;
import org.springframework.stereotype.Service;
import service.ArticleService;
import utils.UUIDUtils;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;
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

    @Override
    public void insertArticle(Article article) throws Exception {

//        生成新的主键
        article.setId(UUIDUtils.getId());

//        设置发布时间为当前时间
        Date nowDate = new Date();
        Timestamp timestamp = new Timestamp(nowDate.getTime());

        article.setDatePublish(timestamp);
        articleDao.insert(article);
    }

    @Override
    public void deleteArticle(Article article) {
        articleDao.delete(article);
    }

    @Override
    public void updateArticle(Article article) {
        articleDao.updateByPrimaryKey(article);
    }

    @Override
    public Article selectArticle(Article article) {
        Article getArticle = articleDao.selectByPrimaryKey(article);
//        List<Article> articleList = articleDao.select(article);
//        return articleList.get(0);
        return getArticle;
    }

    /**
     * 对资讯进行分页查询
     *
     * @param page 第几页
     * @param size 每一页的资讯数
     * @return
     */
    @Override
    public List<Article> selectArticleAll(int page, int size) {
//        查询到所有的资讯
        List<Article> articleList = articleDao.selectAll();

//        如果当查询到的数据小于一页的时候直接返回数据
        if (articleList.size() <= size) {
            return articleList;
        } else {
//        分页开始List标号
            int articleListEnd = page * size - 1;
//        分页结束List标号
            int articleListStart = (page - 1) * size;
//        截取需要的分页资讯
            return articleList.subList(articleListStart, articleListEnd);
        }

    }

    /**
     * 查询所有符合封装条件的文章
     *
     * @param article 封装要查询的属性
     * @return 查询到的文章集合
     */
    @Override
    public List<Article> selectArticles(Article article) {
        List<Article> articleList = articleDao.select(article);
        return articleList;
    }
}
