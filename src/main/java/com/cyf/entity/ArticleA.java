package com.cyf.entity;

import java.io.Serializable;

/**
 * (AtricleA)实体类
 *
 * @author makejava
 * @since 2022-04-16 16:16:17
 */
public class ArticleA implements Serializable {
    private static final long serialVersionUID = 941775125255284052L;
    
    private String student_id;
    
    private String teacher_id;
    
    private String article_a;
    
    private String article_a_statu;

    private String statement;

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(String teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getArticle_a() {
        return article_a;
    }

    public void setArticle_a(String article_a) {
        this.article_a = article_a;
    }

    public String getArticle_a_statu() {
        return article_a_statu;
    }

    public void setArticle_a_statu(String article_a_statu) {
        this.article_a_statu = article_a_statu;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }
}

