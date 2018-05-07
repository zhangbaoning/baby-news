package controller;

import dto.CommentRespDTO;
import entity.Article;
import entity.Comment;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.ArticleService;
import service.CommentService;
import service.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author zhangbaoning
 * @Date 2018/4/13
 */
@Controller
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;
    /**
     * 为资讯添加评论
     *
     * @param comment 评论
     *
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public void addComment(@RequestBody Comment comment) {
        // TODO 应该是评论关联资讯的ID
//        通过资讯ID获取到资讯
//        Article article = articleController.getArticle(articleId);
        // FIXME
//        Article article = articleController.getArticle(new Article());
//        将评论进行保存
        commentService.insertComment(comment);
//        获取到保存之后的评论Id
//        comment = commentDao.select(comment).get(0);
//        article.setCategoryId(comment.getId());
//        articleController.updateArticle(article);

    }

    @RequestMapping(value = "/getAll", method = RequestMethod.POST)
    @ResponseBody
    public List<CommentRespDTO> getAllComment() {
        List respList = new ArrayList();
        CommentRespDTO commentRespDTO = new CommentRespDTO();

        List<Comment> commentList = commentService.selectAllComment();

//        通过评论得到文章题目
        for (Comment comment : commentList) {
            Article article = new Article();
//            将评论中的文章ID查询到文章
            article.setId(comment.getArticleId());
            Article getArticle = articleService.selectArticle(article);

            commentRespDTO.setId(comment.getId());

            User user = userService.getUserByPrimaryKey(comment.getUserId());
            commentRespDTO.setNickname(user.getNickname());
            commentRespDTO.setArticle(getArticle.getTitle());
            commentRespDTO.setPublishTime(comment.getPublishTime());
            commentRespDTO.setComment(comment.getContent());

            respList.add(commentRespDTO);

        }
        return respList;
    }

    /**
     * 删除评论
     *
     * @param comment 封装ID了的评论实体
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public void deleteComment(@RequestBody Comment comment) {
        commentService.deleteComment(comment);
    }

    /**
     * 通过文章ID查询评论
     *
     * @param articleId 文章ID
     */
    @RequestMapping(value = "/getById", method = RequestMethod.GET)
    @ResponseBody
    public List<Comment> getCommentById(@RequestParam String articleId) {
        List<Comment> commentList = commentService.selectCommentsById(articleId);
        return commentList;
    }
}
