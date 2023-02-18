package com.cyf.controller;

import com.cyf.entity.Statu;
import com.cyf.entity.User;
import com.cyf.service.UserService;
import com.cyf.utils.ValidateImageCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;


@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    /**
     * 注册方法
     *
     * @param user
     * @param code
     * @param session
     * @return
     */
    @PostMapping("/register")
    public String register(User user, String code, HttpSession session) {
        String sessionCode = (String) session.getAttribute("code");
        if (sessionCode.equalsIgnoreCase(code)) {
           /* userService.register(user);
            return "redirect:/index";
        }*/
            try {
                userService.register(user);
                System.out.println("注册成功");
                return "redirect:/index";
            } catch (Exception ex) {
                System.out.println("注册失败");
                return "redirect:/toRegister";
            }
        } else {
            System.out.println("验证码错误");
            return "redirect:/toRegister";
        }
    }

    /*测试方法*/
    @PostMapping(value = "/test")
    public String test(String statement, String sub_teacher_id, String sub_student_id) {
        /*System.out.println("测试");
        System.out.println("文章:"+content);
        System.out.println("tid:"+sub_teacher_id);
        System.out.println("sid:"+statement);*/
        System.out.println("批注:" + statement);
        System.out.println(sub_teacher_id);
        System.out.println(sub_student_id);
        return "redirect:/index";
    }

    @PostMapping("/super")
    public String Super(Statu statu, String code, HttpSession session) {
        String sessionCode = (String) session.getAttribute("code");
        if (sessionCode.equalsIgnoreCase(code)) {
            userService.Super(statu);
            return "redirect:/emp/findAll";
        } else {
            return "redirect:/emp/findAll";
        }
    }

    /*管理员新增人员用*/
    @PostMapping("/super_register")
    public String super_register(User user) {
        try {
            userService.register(user);
            return "redirect:/emp/findAll";
        } catch (Exception ex) {
            return "redirect:/emp/findAll";
        }

    }

    /*
     * 管理员修改数据
     * */
    @PostMapping("/change_manager")
    public String change_manager(User user, String code, HttpSession session) {
        String sessionCode = (String) session.getAttribute("code");
        if (sessionCode.equalsIgnoreCase(code)) {
            try {
                userService.change_manager(user.getUsername(), user.getPassword(), user.getPhone(), user.getJob());

                System.out.println(user.getUsername());
                System.out.println(user.getPassword());
                System.out.println(user.getPhone());
                System.out.println(user.getJob());

                System.out.println("change successfully");
                return "redirect:/emp/findAll";
            } catch (Exception ex) {
                System.out.println("change fail");
                return "redirect:/emp/findAll";
            }
        } else {
            return "ems/change_mumber";
        }
    }


    /**
     * 生成验证码
     *
     * @param session
     * @param response
     * @throws IOException
     */
    @GetMapping("/code")
    public void getVerification(HttpSession session, HttpServletResponse response) throws IOException {
        //生成验证码
        String securityCode = ValidateImageCodeUtils.getSecurityCode();
        BufferedImage image = ValidateImageCodeUtils.createImage(securityCode);
        //存入session中
        session.setAttribute("code", securityCode);
        //响应图片
        ServletOutputStream os = response.getOutputStream();
        ImageIO.write(image, "png", os);
    }

    @PostMapping(value = "/change_mumber")
    public String change_mum() {
        return "redirect:/change_mumber";
    }

    @PostMapping(value = "/login")
    public String login(String username, String password, Statu statu) {
        User user = userService.login(username, password);
        if (userService.login(username, password) == null) {
            return "redirect:/index";
        } else {
            String job = user.getJob();
            if (job.equals("学生")) {
                //Statu statu = userService.query(statu);
                statu = userService.query();
                switch (statu.getStatu()) {
                    case "a":
                        System.out.println("a");
                        return "redirect:/emp/findSub?stu_id=" + username;
                    case "b":
                        System.out.println("b");
                        return "redirect:/emp/stu_b?student_id=" + username;
                    case "c":
                        System.out.println("c");
                        return "redirect:/emp/stu_c?id=" + username;
                    case "d":
                        System.out.println("d");
                        return "redirect:/emp/stu_d?id=" + username;
                    case "e":
                        System.out.println("e");
                        return "redirect:/emp/stu_d?id=" + username;
                }
            } else if (job.equals("教师")) {
                statu = userService.query();
                switch (statu.getStatu()) {
                    case "a":
                        System.out.println("a");
                        return "redirect:/emp/t_Sub?id=" + username;
                    case "b":
                        System.out.println("b");
                        return "redirect:/emp/tea_b?id=" + username;
                    case "c":
                        System.out.println("c");
                        return "redirect:/emp/tea_c?id=" + username;
                    case "d":
                        System.out.println("d");
                        return "redirect:/emp/tea_d?id=" + username;
                    case "e":
                        System.out.println("e");
                        return "redirect:/emp/tea_d?id=" + username;
                }
            } else if (job.equals("管理员")) {
                return "redirect:/emp/findAll";
            }
        }
        return "redirect:/index";
    }

    @PostMapping(value = "/add_subject")
    public String add_subject(String teacher_id, String teacher_subject) {
        System.out.println(teacher_id);
        System.out.println(teacher_subject);
        userService.add_subject(teacher_id, teacher_subject);
        System.out.println("课题添加成功");
        return "redirect:/emp/t_Sub?id=" + teacher_id;
    }

    /*学生在b阶段增加文章*/
    @PostMapping(value = "/stu_addarticle")
    public String stu_addarticle(String content, String sub_teacher_id, String sub_student_id) {
        /*System.out.println("测试");
        System.out.println("文章:"+content);
        System.out.println("tid:"+sub_teacher_id);
        System.out.println("sid:"+sub_student_id);*/
        userService.add_articleA(sub_student_id, sub_teacher_id, content);
        return "redirect:/emp/stu_b?stu_id=" + sub_student_id;
    }

    /*学生在b阶段修改文章*/
    @PostMapping(value = "/stu_updatearticle")
    public String stu_updatearticle(String content, String sub_teacher_id, String sub_student_id) {
        /*System.out.println("测试");
        System.out.println("文章:"+content);
        System.out.println("tid:"+sub_teacher_id);
        System.out.println("sid:"+sub_student_id);*/
        userService.update_articleA(sub_student_id, sub_teacher_id, content);
        return "redirect:/emp/stu_b?stu_id=" + sub_student_id;
    }

    /*教师b阶段提交批注和审批*/
    @PostMapping(value = "/setstatement")
    public String setstatement(String statement, String sub_teacher_id, String sub_student_id) {
        /*System.out.println("测试");
        System.out.println("文章:"+content);
        System.out.println("tid:"+sub_teacher_id);
        System.out.println("sid:"+statement);*/
        System.out.println("批注:" + statement);
        System.out.println(sub_teacher_id);
        System.out.println(sub_student_id);
        userService.setstatement(sub_student_id, sub_teacher_id, statement);
        return "redirect:/emp/tea_b?id=" + sub_teacher_id;
    }

    @PostMapping(value = "/setstatu")
    public String setstatu(String sub_teacher_id, String sub_student_id) {
        System.out.println(sub_teacher_id);
        System.out.println(sub_student_id);
        userService.setstatu(sub_student_id, sub_teacher_id);
        return "redirect:/emp/tea_b?id=" + sub_teacher_id;
    }

    /*c阶段文件上传*/
    @PostMapping(value = "/upload")
    public String upload(String teacher_id, String student_id, @RequestParam("file") MultipartFile file) {
        //获取文件的名字
        String filename = file.getOriginalFilename();
        //获取文件后缀名
        String suffix = filename.substring(filename.lastIndexOf("."));
        //上传的文件放在D盘下的upload文件夹中
        String path = "/d:/upload_d/";
        //String path = "/d:/upload_d/";
        //防止文件名重复  随机文件名
        filename = path + UUID.randomUUID() + suffix;
        File f = new File(filename);
        //如果D盘下没有upload文件夹 则创建一个
        if (!f.getParentFile().exists()) {
            f.getParentFile().mkdirs();
        }
        try {
            //把MultipartFile转化为File类型
            file.transferTo(f);
            userService.upload(student_id, teacher_id, filename);
            return "redirect:/emp/stu_c?id=" + student_id;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("error");
            return "redirect:/emp/stu_c?id=" + student_id;
        }
    }

    @RequestMapping("/download")
    public String download(String teacher_id, String student_id, HttpServletResponse response) {

        try {
            String path = userService.download(student_id, teacher_id);
            // 文件地址，真实环境是存放在数据库中的
            File file = new File(path);
            // 创建输入流，传入文件对象
            FileInputStream fis = new FileInputStream(file);
            // 设置相关格式
            response.setContentType("application/force-download");
            // 设置下载后的文件名以及header
            response.addHeader("Content-disposition", "attachment;filename=text.docx");
            OutputStream os = response.getOutputStream();
            // 常规操作
            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = fis.read(buf)) != -1) {
                os.write(buf, 0, len);
            }
            os.close();
            fis.close();
            return "redirect:/emp/tea_c?id=" + teacher_id;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("tea_c error");
            return "redirect:/emp/tea_c?id=" + teacher_id;
        }
    }

    /*c阶段教师审批*/
    @RequestMapping(value = "/c_pass")
    public String c_pass(String teacher_id, String student_id) {
        userService.c_pass(student_id, teacher_id);
        return "redirect:/emp/tea_c?id=" + teacher_id;
    }

    /*学生在d阶段上传*/
    @PostMapping(value = "/stu_add_d")
    public String stu_add_d(String content, String teacher_id, String student_id, @RequestParam("file") MultipartFile file, Model model) {
        //获取文件的名字
        String filename = file.getOriginalFilename();
        //获取文件后缀名
        String suffix = filename.substring(filename.lastIndexOf("."));
        //上传的文件放在D盘下的upload文件夹中
        String path = "/d:/upload_d/";
        /*String path = "finalwork\\upload_d\\";*/
        //防止文件名重复  随机文件名
        filename = path + UUID.randomUUID() + suffix;
        File f = new File(filename);
        //如果D盘下没有upload文件夹 则创建一个
        if (!f.getParentFile().exists()) {
            f.getParentFile().mkdirs();
        }
        String msg = "success";
        try {
            //把MultipartFile转化为File类型
            file.transferTo(f);
            userService.upload_d(student_id, teacher_id, content, filename);
            model.addAttribute("id", student_id);
            model.addAttribute("teacher_id", teacher_id);
            model.addAttribute("msg", msg);
            return "ems/student_d";
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("error");
            msg = "error";
            model.addAttribute("id", student_id);
            model.addAttribute("teacher_id", teacher_id);
            model.addAttribute("msg", msg);
            return "redirect:/emp/stu_d";
        }
    }

    @RequestMapping("/download2")
    public String download2(String teacher_id, String student_id, HttpServletResponse response) {
        try {
            String path = userService.download2(student_id, teacher_id);
            // 文件地址，真实环境是存放在数据库中的
            File file = new File(path);
            // 创建输入流，传入文件对象
            FileInputStream fis = new FileInputStream(file);
            // 设置相关格式
            response.setContentType("application/force-download");
            // 设置下载后的文件名以及header
            response.addHeader("Content-disposition", "attachment;filename=text.docx");
            OutputStream os = response.getOutputStream();
            // 常规操作
            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = fis.read(buf)) != -1) {
                os.write(buf, 0, len);
            }
            os.close();
            fis.close();
            return "redirect:/emp/tea_d?id=" + teacher_id;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("tea_c error");
            return "redirect:/emp/tea_d?id=" + teacher_id;
        }
    }

    /*教师d阶段提交批注和审批*/
    @PostMapping(value = "/setscore")
    public String setscore(String score, String teacher_id, String student_id) {
        /*System.out.println("测试");
        System.out.println("文章:"+content);
        System.out.println("tid:"+sub_teacher_id);
        System.out.println("sid:"+statement);*/
        System.out.println("score:" + score);
        userService.setscore(student_id, teacher_id, score);
        return "redirect:/emp/tea_d?id=" + teacher_id;
    }
}