package service;

import entity.Article;

import java.util.List;

/**
 * @Description
 * @Author zhangbaoning
 * @Date 2018/4/10
 */
public interface ArticleService {
    /**
     * 保存
     *
     * @param article
     */
    void insertArticle(Article article) throws Exception;

    /**
     * 删除
     *
     * @param article
     */
    void deleteArticle(Article article);

    /**
     * 更新
     *
     * @param article
     */

    void updateArticle(Article article);

    /**
     * 查询
     *
     * @param article
     * @return
     */

    Article selectArticle(Article article);

    /**
     * 对资讯进行分页查询
     *
     * @param page 第几页
     * @param size 每一页的资讯数
     * @return
     */
    List<Article> selectArticleAll(int page, int size);

    /**
     * 查询所有符合封装条件的文章
     *
     * @param article 封装要查询的属性
     * @return 查询到的文章集合
     */
    List<Article> selectArticles(Article article);
}
