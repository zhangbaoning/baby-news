package controller;

import dao.CommentDao;
import dto.CommentRespDTO;
import entity.Article;
import entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.ArticleService;
import service.CommentService;

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
    private CommentDao commentDao;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private CommentService commentService;

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
//        Article article = articleController.getArticle(new Article());
//        将评论进行保存
        commentDao.insert(comment);
//        获取到保存之后的评论Id
        comment = commentDao.select(comment).get(0);
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
            commentRespDTO.setNickname(comment.getNickname());
            commentRespDTO.setArticle(getArticle.getTitle());
            commentRespDTO.setPublishTime(comment.getPublishTime());
            commentRespDTO.setComment(comment.getContent());

            respList.add(commentRespDTO);

        }
        return respList;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public void deleteComment(@RequestBody Comment comment) {
        commentService.deleteComment(comment);
    }
}
