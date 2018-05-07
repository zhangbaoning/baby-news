package controller;

import entity.Article;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.ArticleService;
import service.UserService;

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
    @Autowired
    private UserService userService;

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
    public void updateArticle(@RequestBody Article article) {
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
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public Article getArticle(@RequestParam String articleId) {
        Article articleResp = null;
        try {
            Article articleReq = new Article();
            articleReq.setId(articleId);
            articleResp = articleService.selectArticle(articleReq);
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
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ResponseBody
    public List<Article> getArticleAll() {
        Integer page = 1;
        Integer size = 10;
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
        Article article = this.getArticle(articleId);
//        Article article = this.getArticle(new Article());
//         设置分类类型
//        TODO 分类ID需要去调整
        article.setCategoryId(categoryId);
//        对设置分类后的资讯进行更新
        this.updateArticle(article);
    }

    /**
     * 通过文章ID获取到作者详情
     *
     * @return 作者详情
     */
    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    @ResponseBody
    public User getUserByArticleId(@RequestParam String articleId) {

        Article article = new Article();
        article.setId(articleId);
        Article articleResult = articleService.selectArticle(article);
        String userId = articleResult.getUserId();
        User userResp = userService.getUserByPrimaryKey(userId);
//        不能将密码返回前台
        if (userResp != null) {
            userResp.setPassword(null);
        }

        return userResp;
    }


}
