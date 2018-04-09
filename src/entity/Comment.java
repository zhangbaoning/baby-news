package entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Comment {
    private Integer id;
    private String content;
    private String username;
    private Timestamp datePublish;

    @Basic
    @Column(name = "id", nullable = true)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
    @Column(name = "username", nullable = true, length = 255)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "date_publish", nullable = true)
    public Timestamp getDatePublish() {
        return datePublish;
    }

    public void setDatePublish(Timestamp datePublish) {
        this.datePublish = datePublish;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id) &&
                Objects.equals(content, comment.content) &&
                Objects.equals(username, comment.username) &&
                Objects.equals(datePublish, comment.datePublish);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, content, username, datePublish);
    }
}
