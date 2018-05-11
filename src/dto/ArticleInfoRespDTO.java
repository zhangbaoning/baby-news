package dto;

import entity.Article;

/**
 * @Description
 * @Author zhangbaoning
 * @Date 2018/5/11
 */
public class ArticleInfoRespDTO extends Article {
    /**
     * 作者名
     */
    private String author;
    /**
     * 分类名
     */
    private String category;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
