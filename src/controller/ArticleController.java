package controller;

import entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.ArticleService;

import java.util.List;

/**
 * @Description
 * @Author zhangbaoning
 * @Date 2018/4/13
 */
@Controller
@RequestMapping("/article")

public class ArticleController {
    @Autowired
    private ArticleService articleService;

    /**
     * 发布资讯
     *
     * @param article
     */
    @RequestMapping(value = "/post", method = RequestMethod.POST)
    @ResponseBody
    public void postArticle(@RequestBody Article article) {
        try {
            articleService.insertArticle(article);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 对资讯进行删除
     *
     * @param articleId
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public void deleteArticle(String articleId) {
        try {
            Article article = new Article();
            article.setId(articleId);
            articleService.deleteArticle(article);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 对资讯进行更新
     *
     * @param article
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public void updateArticle(Article article) {
        try {
            articleService.updateArticle(article);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取资讯
     *
     * @param articleId 资讯ID
     * @return
     */
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    @ResponseBody
    public Article getArticle(@RequestBody Article article) {
        Article articleResp = null;
        try {
            articleResp = articleService.selectArticle(article);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return articleResp;

    }

    /**
     * 分页查询资讯
     *
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/getAll", method = RequestMethod.POST)
    @ResponseBody
    public List<Article> getArticleAll(Integer page, Integer size) {
        List<Article> articleList = null;
        try {
            articleList = articleService.selectArticleAll(page, size);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return articleList;

    }

    /**
     * 为资讯添加分类
     *
     * @param categoryId 分类ID
     * @param articleId  资讯ID
     */
    @RequestMapping(value = "/addCategory", method = RequestMethod.POST)
    @ResponseBody
    public void addCategoryForArticle(int categoryId, String articleId) {
//        通过资讯ID查询到资讯实体
//        Article article = this.getArticle(articleId);
        Article article = this.getArticle(new Article());
//         设置分类类型
//        TODO 分类ID需要去调整
        article.setCategoryId(categoryId);
//        对设置分类后的资讯进行更新
        this.updateArticle(article);
    }


}
