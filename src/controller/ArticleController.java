package controller;

import dto.ArticleInfoRespDTO;
import entity.Article;
import entity.Category;
import entity.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.ArticleService;
import service.CategoryService;
import service.UserService;

import java.util.LinkedList;
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
    @Autowired
    private CategoryService categoryService;

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
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseBody
    public void deleteArticle(@RequestParam String articleId) {
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
     * @return
     */
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ResponseBody
    public List<ArticleInfoRespDTO> getArticleAll() {
        Integer page = 1;
        Integer size = 10;
        List<Article> articleList;
        List<ArticleInfoRespDTO> dtoList = new LinkedList<>();
        try {
//            分页查询
//    FIXME        articleList = articleService.selectArticleAll(page, size);
            articleList = articleService.selectArticleAll();
//            循环查询外键关联的信息
            for (Article article : articleList) {
                ArticleInfoRespDTO respDTO = new ArticleInfoRespDTO();
                BeanUtils.copyProperties(article, respDTO);
                User user = userService.getUserByPrimaryKey(article.getUserId());
                respDTO.setAuthor(user.getNickname());

                Category category = categoryService.selectByPrimarykey(article.getCategoryId());
                if (category != null) {
                    respDTO.setCategory(category.getName());
                }
                dtoList.add(respDTO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dtoList;

    }

    /**
     * 为资讯添加分类
     *
     * @param categoryId 分类ID
     * @param articleId  资讯ID
     */
    @RequestMapping(value = "/addCategory", method = RequestMethod.POST)
    @ResponseBody
    public void addCategoryForArticle(String categoryId, String articleId) {
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

    @RequestMapping(value = "/getByCategoryId", method = RequestMethod.GET)
    @ResponseBody
    public List<Article> getByCategoryId(@RequestParam String categoryId) {
        Article article = new Article();
        article.setCategoryId(categoryId);
        List<Article> articleList = articleService.selectArticles(article);
        return articleList;
    }


}
