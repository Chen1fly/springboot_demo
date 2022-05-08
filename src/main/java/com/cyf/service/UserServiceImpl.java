package com.cyf.service;

import com.cyf.dao.UserDAO;
import com.cyf.entity.Statu;
import com.cyf.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;

    @Override
    public void register(User user) {
        //user.setUsername();
        userDAO.save(user);
    }


    @Override
    public void Super(Statu statu) {
        //user.setUsername();
        userDAO.Super(statu);
    }

    @Override
    public User login(String username, String password) {

        return userDAO.login(username, password);
    }

    @Override
    public Statu query() {
        return userDAO.query();
    }


    @Override
    public void change_manager(String username, String password, String phone, String job) {
        userDAO.change_manager(username, password, phone, job);
    }

    @Override
    public String t_sub(String username) {

        return userDAO.t_sub(username).getTeacher_id();
    }

    @Override
    public void add_subject(String teacher_id, String teacher_subject) {
        userDAO.add_subject(teacher_id, teacher_subject);
    }

    @Override
    public void add_articleA(String sub_student_id, String sub_teacher_id, String content) {
        userDAO.add_articleA(sub_student_id, sub_teacher_id, content);
    }

    @Override
    public void update_articleA(String sub_student_id, String sub_teacher_id, String content) {
        userDAO.update_articleA(sub_student_id, sub_teacher_id, content);
    }

    @Override
    public void setstatement(String sub_student_id, String sub_teacher_id, String statement) {
        userDAO.setstatement(sub_student_id, sub_teacher_id, statement);
    }

    @Override
    public void setstatu(String sub_student_id, String sub_teacher_id) {
        userDAO.setstatu(sub_student_id, sub_teacher_id);
    }

    @Override
    public void upload(String student_id, String teacher_id, String s) {
        userDAO.upload(student_id, teacher_id, s);
    }

    @Override
    public String download(String student_id, String teacher_id) {
        return userDAO.download(student_id, teacher_id);
    }

    @Override
    public void c_pass(String student_id, String teacher_id) {
        userDAO.c_pass(student_id, teacher_id);
    }

    @Override
    public void upload_d(String student_id, String teacher_id, String content, String filename) {
        userDAO.upload_d(student_id, teacher_id, content, filename);
    }

    @Override
    public String download2(String student_id, String teacher_id) {
        return userDAO.download2(student_id, teacher_id);
    }

    @Override
    public void setscore(String student_id, String teacher_id, String score) {
        userDAO.setscore(student_id, teacher_id, score);
    }

}
