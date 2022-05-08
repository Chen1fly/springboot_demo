package com.cyf.service;

import com.cyf.entity.*;

import java.util.List;

public interface EmpService {
    List<Emp> findAll();

    void save(Emp emp);

    void delete(String id);

    Emp find(String id);

    void update(Emp emp);

    List<Sub> t_sub(String username);//findByTeacherName

    List<ArticleA> tea_b(String teacher_id);//findByTeacherName article

    List<Sub> findSub();

    void stu_choose(String student_id,String teacher_id,String teacher_subject);

    void tea_choose(String student_id, String teacher_id, String teacher_subject);

    Sub stu_search(String student_id);

    String  stu_tea(String id);

    List<ArticleB> tea_c(String id);

    String stu_tea_d(String id);

    String stu_tea_d2(String id);

    List<ArticleC> tea_d(String id);

    String stu_tea_d3(String id);

    String stu_tea_d4(String id);
}
