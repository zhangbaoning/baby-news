package controller;

import dao.CommentDao;
import entity.Article;
import entity.Comment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description
 * @Author zhangbaoning
 * @Date 2018/4/13
 */
@Controller
@RequestMapping("/comment")
public class CommentController {
    private CommentDao commentDao;
    private ArticleController articleController;

    /**
     * 为资讯添加评论
     *
     * @param comment
     * @param articleId
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public void addComment(Comment comment, String articleId) {
        // TODO 应该是评论关联资讯的ID
//        通过资讯ID获取到资讯
//        Article article = articleController.getArticle(articleId);
        // FIXME
        Article article = articleController.getArticle(new Article());
//        将评论进行保存
        commentDao.insert(comment);
//        获取到保存之后的评论Id
        comment = commentDao.select(comment).get(0);
        article.setCategoryId(comment.getId());
        articleController.updateArticle(article);

    }
}
