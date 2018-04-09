package entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Article {
    private int id;
    private String title;
    private String desc;
    private String content;
    private String clickCount;
    private Timestamp datePublish;
    private String userId;
    private String categoryId;
    private Timestamp dateModified;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "title", nullable = true, length = 255)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "desc", nullable = true, length = 255)
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Basic
    @Column(name = "content", nullable = true, length = 255)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "click_count", nullable = true, length = 255)
    public String getClickCount() {
        return clickCount;
    }

    public void setClickCount(String clickCount) {
        this.clickCount = clickCount;
    }

    @Basic
    @Column(name = "date_publish", nullable = true)
    public Timestamp getDatePublish() {
        return datePublish;
    }

    public void setDatePublish(Timestamp datePublish) {
        this.datePublish = datePublish;
    }

    @Basic
    @Column(name = "user_id", nullable = true, length = 255)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "category_id", nullable = true, length = 255)
    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    @Basic
    @Column(name = "date_modified", nullable = true)
    public Timestamp getDateModified() {
        return dateModified;
    }

    public void setDateModified(Timestamp dateModified) {
        this.dateModified = dateModified;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return id == article.id &&
                Objects.equals(title, article.title) &&
                Objects.equals(desc, article.desc) &&
                Objects.equals(content, article.content) &&
                Objects.equals(clickCount, article.clickCount) &&
                Objects.equals(datePublish, article.datePublish) &&
                Objects.equals(userId, article.userId) &&
                Objects.equals(categoryId, article.categoryId) &&
                Objects.equals(dateModified, article.dateModified);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, title, desc, content, clickCount, datePublish, userId, categoryId, dateModified);
    }
}
