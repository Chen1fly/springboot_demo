
# 毕业设计管理系统
###流程：
    选题：
        师生双选
            1.已申请的学生
            2.我开设的题目
        学生选题
    开题：
        学生开题报告提交
        老师审核
    中期检查：
        学生提交中期文档
        教师批注审核
    毕业设计提交：
        学生提交
    评分：
        教师评分



## 目录

- [文件目录说明](#文件目录说明)
- [开发的架构](#开发的架构)
- [部署](#部署)
- [使用到的框架](#使用到的框架)
- [作者](#作者)


### 文件目录说明


```
├─main
│  ├─java
│  │  └─com
│  │      └─cyf
│  │          │  EmsThymeleafApplication.java
│  │          │  
│  │          ├─controller
│  │          │      EmpController.java
│  │          │      IndexController.java
│  │          │      UserController.java
│  │          │      
│  │          ├─dao
│  │          │      EmpDAO.java
│  │          │      UserDAO.java
│  │          │      
│  │          ├─entity
│  │          │      Emp.java
│  │          │      Statu.java
│  │          │      Sub.java
│  │          │      User.java
│  │          │      
│  │          ├─service
│  │          │      EmpService.java
│  │          │      EmpServiceImpl.java
│  │          │      UserService.java
│  │          │      UserServiceImpl.java
│  │          │      
│  │          └─utils
│  │                  ValidateImageCodeUtils.java
│  │                  
│  └─resources
│      │  application.properties
│      │  
│      ├─com
│      │  └─cyf
│      │      ├─mapper
│      │      │      EmpDAOMapper.xml
│      │      │      UserDAOMapper.xml
│      │      │      
│      │      └─sql
│      │              init.sql
│      │              
│      ├─static
│      │  ├─css
│      │  │      style.css
│      │  │      style.css.bak1
│      │  │      
│      │  └─img
│      │          1920.png
│      │          bg.gif
│      │          bground.png
│      │          bullet_green.gif
│      │          bullet_grey.gif
│      │          button.gif
│      │          content_bg.gif
│      │          footer.gif
│      │          footer_bg.gif
│      │          nane.gif
│      │          tableheader-bg-grey.gif
│      │          tableheader-bg.gif
│      │          top_left.gif
│      │          
│      └─templates
│          └─ems
│                  add_mumber.html
│                  change_mumber.html
│                  emplist.html
│                  login.html
│                  regist.html
│                  student_a.html
│                  teacher_a.html
│                  updateEmp.html
│                  
└─test
    └─java
        └─com
            └─cyf
                └─ems_thymeleaf
                        EmsThymeleafApplicationTests.java
                        

```





### 开发的架构

暂无
### 部署

暂无

### 使用到的框架

- SpringBoot
- 前端框架thymeleaf
- 持久层框架Mybatis

### 作者

chenyi.fei@163.com

*@Chenyifei*


