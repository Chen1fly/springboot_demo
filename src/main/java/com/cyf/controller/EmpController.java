package com.cyf.controller;

import com.cyf.entity.*;
import com.cyf.service.EmpService;
import com.cyf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/emp")
public class EmpController {

    @Autowired
    private EmpService empService;
    private UserService userService;

    @GetMapping(value = "/findAll")
    //find和findAll不是重定向，直接到跳转页面，并且直接跳转classpath下template自带/
    //重定向的话不是从template下，是需要加/的
    //查找不是重定向，不加/；重定向需要加/
    public String findAll(Model model) {
        List<Emp> allList = empService.findAll();
        model.addAttribute("emps", allList);
        return "ems/emplist";
    }


    @PostMapping(value = "/save")
    public String save(Emp emp) {
        empService.save(emp);
        return "redirect:/emp/findAll";
    }

    @GetMapping(value = "/delete")
    public String delete(String id) {
        empService.delete(id);
        return "redirect:/emp/findAll";
    }

    /*管理员修改信息*/
    @GetMapping(value = "/manager_change")
    public String manager_change(Model model, String id) {
        model.addAttribute("id", id);
        return "ems/change_mumber";
    }

    @GetMapping(value = "/find")
    public String find(String id, Model model) {
        Emp emp = empService.find(id);
        model.addAttribute("emp", emp);
        return "ems/updateEmp";
    }

    @PostMapping(value = "update")
    public String update(Emp emp) {
        empService.update(emp);
        return "redirect:/emp/findAll";
    }

    /*
    query方法 登陆之前先查询数据库statu，查看目前的流程状态
    * */
    @PostMapping(value = "query")
    public String query(Emp emp) {
        empService.update(emp);
        return "redirect:/emp/findAll";
    }

    /*教师查询课题*/
    @GetMapping(value = "/t_Sub")
    public String t_Sub(Model model, String id) {
        //System.out.println(id);
        List<Sub> allList = empService.t_sub(id);
        //System.out.println(allList);
        model.addAttribute("subs", allList);
        //System.out.println(allList.get(1).getTeacher_id());
        model.addAttribute("tea", allList.get(1).getTeacher_id());
        return "ems/teacher_a";
        /*try {
            //String user = (String) model.getAttribute("id");
            String temp = empService.t_sub(id);
            System.out.println(temp);
            model.addAttribute("sub", temp);
            return "ems/teacher_a";
        }
        catch (Exception ex){
            ex.printStackTrace();
            return "ems/teacher_a";
        }*/
    }

    @GetMapping(value = "/findSub")
    public String findSub(Model model, Model model1, String stu_id) {
        List<Sub> allList = empService.findSub();
        model.addAttribute("subs", allList);
        model1.addAttribute("stu_id", stu_id);
        return "ems/student_a";
    }

    /*学生选课*/
    @GetMapping(value = "/stu_choose")
    public String stu_choose(String student_id, String teacher_id, String teacher_subject) {
        empService.stu_choose(student_id, teacher_id, teacher_subject);
        System.out.println("学生选课成功");
        return "redirect:/emp/findSub?stu_id=" + student_id;
    }

    /*学生选课*/
    @GetMapping(value = "/tea_choose")
    public String tea_choose(String student_id, String teacher_id, String teacher_subject) {
        empService.tea_choose(student_id, teacher_id, teacher_subject);
        System.out.println("教师双选成功");
        return "redirect:/emp/t_Sub?id=" + teacher_id;
    }

    /*学生b阶段*/
    @GetMapping(value = "/stu_b")
    public String stu_b(Model model, String stu_id) {
        //System.out.println(id);
        //List<Sub> allList = empService.t_sub(id);
        //System.out.println(allList);
        //model.addAttribute("subs", allList);
        //System.out.println(allList.get(1).getTeacher_id());
        //model.addAttribute("tea", allList.get(1).getTeacher_id());
        try {
            System.out.println(stu_id);
            Sub sub = empService.stu_search(stu_id);
            model.addAttribute("sub",sub);
            return "ems/student_b";
        }catch (Exception ex){
            System.out.println("学生信息有误，可能未进行双选");
            return "ems/index";
        }

        /*try {
            //String user = (String) model.getAttribute("id");
            String temp = empService.t_sub(id);
            System.out.println(temp);
            model.addAttribute("sub", temp);
            return "ems/teacher_a";
        }
        catch (Exception ex){
            ex.printStackTrace();
            return "ems/teacher_a";
        }*/
    }
    /*b阶段教师操作*/
    @GetMapping(value = "/tea_b")
    public String tea_b(Model model, String id) {
        //System.out.println(id);
        List<ArticleA> allList = empService.tea_b(id);
        //System.out.println(allList);
        model.addAttribute("subs", allList);
        //System.out.println(allList.get(1).getTeacher_id());
        model.addAttribute("tea", allList.get(0).getTeacher_id());
        return "ems/teacher_b";
        /*try {
            //String user = (String) model.getAttribute("id");
            String temp = empService.t_sub(id);
            System.out.println(temp);
            model.addAttribute("sub", temp);
            return "ems/teacher_a";
        }
        catch (Exception ex){
            ex.printStackTrace();
            return "ems/teacher_a";
        }*/
    }
    /*c阶段学生操作*/
    @GetMapping(value = "/stu_c")
    public String stu_c(Model model, String id) {
        try {
            model.addAttribute("id",id);
            String teacher_id = empService.stu_tea(id);
            model.addAttribute("teacher_id",teacher_id);
            return "ems/student_c";
        }
        catch (Exception ex){
            ex.printStackTrace();
            System.out.println("stu_c error");
            return "ems/student_c";
        }
    }
    /*c阶段教师操作*/
    @GetMapping(value = "/tea_c")
    public String tea_c(Model model, String id) {
        try {
            model.addAttribute("id",id);
            List<ArticleB> allList = empService.tea_c(id);
            model.addAttribute("articleb",allList);
            return "ems/teacher_c";
        }
        catch (Exception ex){
            ex.printStackTrace();
            System.out.println("tea_c error");
            return "ems/teacher_c";
        }
    }
    /*d阶段学生操作*/
    @GetMapping(value = "/stu_d")
    public String stu_d(Model model, String id) {
        //System.out.println(empService.stu_tea_d2(id));
        if (empService.stu_tea_d(id).equals("通过")) {
            try {
                model.addAttribute("id", id);
                String teacher_id = empService.stu_tea_d2(id);
                String score = empService.stu_tea_d3(id);
                String subject = empService.stu_tea_d4(id);
                if (score.contains("分")){
                    model.addAttribute("teacher_id", teacher_id);
                    model.addAttribute("msg","null");
                    model.addAttribute("score",score);
                    model.addAttribute("subject",subject);
                    return "ems/student_e";
                }else {
                    model.addAttribute("teacher_id", teacher_id);
                    model.addAttribute("msg","null");
                    return "ems/student_d";
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("stu_d error可能未上传");
                String teacher_id = empService.stu_tea_d2(id);
                model.addAttribute("teacher_id", teacher_id);
                model.addAttribute("msg","null");
                return "ems/student_d";
            }
        }else{
            return "ems/student_d";
        }
    }
    /*D阶段教师操作*/
    @GetMapping(value = "/tea_d")
    public String tea_d(Model model, String id) {
        try {
            List<ArticleC> allList = empService.tea_d(id);
            model.addAttribute("id",id);
            model.addAttribute("articlec",allList);
            return "ems/teacher_d";
        }
        catch (Exception ex){
            ex.printStackTrace();
            System.out.println("tea_d error");
            return "ems/teacher_d";
        }
    }
}
