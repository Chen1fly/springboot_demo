package com.cyf.service;

import com.cyf.dao.EmpDAO;
import com.cyf.dao.UserDAO;
import com.cyf.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpDAO empDAO;
    private UserDAO userDAO;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Emp> findAll() {
        return empDAO.findAll();
    }

    @Override
    public void save(Emp emp) {
        emp.setUsername(UUID.randomUUID().toString());
        empDAO.save(emp);
    }

    @Override
    public void delete(String id) {
        empDAO.delete(id);
    }

    @Override
    public Emp find(String id) {
        return null;
    }


    @Override
    public void update(Emp emp) {
        empDAO.update(emp);
    }

    public String findSubject(String id) {
        return null;
    }
    @Override
    public List<Sub> t_sub(String username) {

        return empDAO.t_sub(username);
    }

    @Override
    public List<ArticleA> tea_b(String teacher_id) {
        return empDAO.tea_b(teacher_id);
    }

    @Override
    public List<Sub> findSub() {
        return empDAO.findSub();
    }

    @Override
    public void stu_choose(String student_id, String teacher_id, String teacher_subject) {
        empDAO.stu_choose(student_id, teacher_id, teacher_subject);
    }

    @Override
    public void tea_choose(String student_id, String teacher_id, String teacher_subject) {
        empDAO.tea_choose(student_id, teacher_id, teacher_subject);
    }

    @Override
    public Sub stu_search(String student_id) {
        return empDAO.stu_search(student_id);
    }

    @Override
    public String stu_tea(String id) {
        return empDAO.stu_tea(id);
    }

    @Override
    public List<ArticleB> tea_c(String id) {
        return empDAO.tea_c(id);
    }

    @Override
    public String stu_tea_d(String id) {
        return empDAO.stu_tea_d(id);
    }

    @Override
    public String stu_tea_d2(String id) {
        return empDAO.stu_tea_d2(id);
    }

    @Override
    public List<ArticleC> tea_d(String id) {
        return empDAO.tea_d(id);
    }

    @Override
    public String stu_tea_d3(String id) {
        return empDAO.stu_tea_d3(id);
    }

    @Override
    public String stu_tea_d4(String id) {
        return empDAO.stu_tea_d4(id);
    }


}
