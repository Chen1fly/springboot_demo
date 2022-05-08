package com.cyf.dao;

import com.cyf.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmpDAO {
    List<Emp> findAll();

    void save(Emp emp);

    void delete(String id);

    void update(String username);

    void update(Emp emp);

    List<Sub> t_sub(@Param("username") String username);

    List<Sub> findSub();

    void stu_choose(@Param("student_id") String student_id,@Param("teacher_id") String teacher_id,@Param("teacher_subject") String teacher_subject);

    void tea_choose(@Param("student_id") String student_id,@Param("teacher_id") String teacher_id,@Param("teacher_subject") String teacher_subject);

    Sub stu_search(@Param("student_id") String student_id);

    List<ArticleA> tea_b(@Param("teacher_id") String teacher_id);

    String stu_tea(@Param("student_id") String id);

    List<ArticleB> tea_c(@Param("teacher_id") String id);

    String stu_tea_d(@Param("student_id") String id);

    String stu_tea_d2(@Param("student_id") String id);

    List<ArticleC> tea_d(@Param("teacher_id") String id);

    String stu_tea_d3(@Param("student_id") String id);

    String stu_tea_d4(@Param("student_id") String id);
}
