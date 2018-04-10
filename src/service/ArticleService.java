package service;

import entity.Article;

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
    void insertArticle(Article article);

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
}
