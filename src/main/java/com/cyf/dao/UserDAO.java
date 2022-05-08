package com.cyf.dao;

import com.cyf.entity.Statu;
import com.cyf.entity.Sub;
import com.cyf.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserDAO {
    void save(User user);

    //在mybatis中传递多个参数需要参数的绑定
    User login(@Param("username") String username, @Param("password") String password);

    //super流程管理
    void Super(Statu statu);

    Statu query();

    //管理员修改人员
    void change_manager(@Param("username") String username, @Param("password") String password,
                        @Param("phone") String phone, @Param("job") String job);

    //教师课题查询
    //String t_Sub(@Param("username") String username);

    void change_manager(String username);

    Sub t_sub(@Param("username") String username);

    void add_subject(@Param("teacher_id") String teacher_id,@Param("teacher_subject") String teacher_subject);

    void add_articleA(@Param("student_id")String sub_student_id, @Param("teacher_id")String sub_teacher_id, @Param("article_a")String content);

    void update_articleA(@Param("student_id")String sub_student_id, @Param("teacher_id")String sub_teacher_id, @Param("article_a")String content);

    void setstatement(@Param("student_id")String sub_student_id, @Param("teacher_id")String sub_teacher_id, @Param("statement")String statement);

    void setstatu(@Param("student_id")String sub_student_id, @Param("teacher_id")String sub_teacher_id);

    void upload(@Param("student_id")String student_id,@Param("teacher_id") String teacher_id,@Param("path")String s);

    String download(@Param("student_id") String student_id,@Param("teacher_id") String teacher_id);

    void c_pass(@Param("student_id") String student_id,@Param("teacher_id") String teacher_id);

    void upload_d(@Param("student_id") String student_id,@Param("teacher_id") String teacher_id,@Param("content") String content,@Param("path") String filename);

    String download2(@Param("student_id") String student_id,@Param("teacher_id") String teacher_id);

    void setscore(@Param("student_id") String student_id,@Param("teacher_id") String teacher_id,@Param("statu")  String score);
}
