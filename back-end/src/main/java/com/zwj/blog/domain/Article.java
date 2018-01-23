package com.zwj.blog.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "t_article")
public class Article implements Serializable{
    @Id
    @GenericGenerator(name = "uuidGenerator", strategy = "uuid")
    @GeneratedValue(generator = "uuidGenerator")
    @Column(name = "id")
    private String id;
    @Column(name = "category_id")
    private String categoryId; //分类id
    @Column(name = "title")
    private String title;   //标题
    @Column(name = "content")
    private String content;  //内容
    @Column(name = "description")
    private String description; //描述
    @Column(name = "status")
    private Integer status;  //状态
    @Column(name = "author_name")
    private String authorName; //作者
    @Column(name = "create_time")
    private Date createTime;    //创建时间
    @Column(name = "updateTime")
    private Date updateTime;    //更新时间
    @Column(name = "show_count")
    private Integer showCount;  //浏览数
    @OneToMany
    @JoinColumn(name="article_id", referencedColumnName = "id")
    private List<ArticleTag> tagList;   //标签列表

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getShowCount() {
        return showCount;
    }

    public void setShowCount(Integer showCount) {
        this.showCount = showCount;
    }

    public List<ArticleTag> getTagList() {
        return tagList;
    }

    public void setTagList(List<ArticleTag> tagList) {
        this.tagList = tagList;
    }
}
