package com.spring.controller;

import com.spring.domain.Role;
import com.spring.domain.User;
import com.spring.service.RoleService;
import com.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

//    用户管理界面信息
    @RequestMapping("list")
    public ModelAndView list(){
        ModelAndView modelAndView = new ModelAndView();
        List<User> userList = userService.list();
        modelAndView.addObject("userList",userList);
        modelAndView.setViewName("user-list");
        return modelAndView;
    }

//    新建用户用户角色查询
    @RequestMapping("saveUI")
    public ModelAndView saveUI(){
        ModelAndView modelAndView = new ModelAndView();
        List<Role> roles = roleService.list();
        modelAndView.addObject("roleList",roles);
        modelAndView.setViewName("user-add");
        return modelAndView;
    }

//    新建用户
    @RequestMapping("save")
    public String save(User user, long[] roleIds){
        userService.save(user,roleIds);
        return "redirect:/user/list";
    }

//    删除用户
    @RequestMapping("del/{userId}")
    public String del(@PathVariable("userId") long userId){
        userService.del(userId);
        return "redirect:/user/list";
    }

//    用户登录
    @RequestMapping("login")
    public String login(User user, HttpSession session){
//        查询数据库判断用户名密码是否正确
        Boolean flag = userService.login(user);
        if (flag){
            session.setAttribute("user",user);
            return "redirect:/index.jsp";
        }
        return "redirect:/login.jsp";
    }
}
