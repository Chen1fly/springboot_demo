package com.cyf.service;

import com.cyf.entity.Statu;
import com.cyf.entity.Sub;
import com.cyf.entity.User;

public interface UserService {

    void register(User user);

    void Super(Statu statu);

    User login(String username, String password);

    Statu query();

    void change_manager(String username, String password, String phone, String job);

    String t_sub(String username);

    void add_subject(String teacher_id,String teacher_subject);

    void add_articleA(String sub_student_id, String sub_teacher_id, String content);

    void update_articleA(String sub_student_id, String sub_teacher_id, String content);

    void setstatement(String sub_student_id, String sub_teacher_id, String statement);

    void setstatu(String sub_student_id, String sub_teacher_id);

    void upload(String student_id, String teacher_id, String s);

    String download(String student_id, String teacher_id);

    void c_pass(String student_id, String teacher_id);

    void upload_d(String student_id, String teacher_id,String content, String filename);

    String download2(String student_id, String teacher_id);

    void setscore(String student_id, String teacher_id, String score);

    Sub stu_search(String student_id);
}
