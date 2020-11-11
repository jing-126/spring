package com.spring_mvc.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring_mvc.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    @RequestMapping("/quick")
    public String save(){
        System.out.println("userController save running...");
        return "success";
    }

    @RequestMapping("/mav01")
    public ModelAndView mav01(){
        ModelAndView mav = new ModelAndView();
//        设置模型数据
        mav.addObject("username","jing");
//        设置视图
        mav.setViewName("success");
        return mav;
    }

    @RequestMapping("/mav02")
//    SpringMVC注入ModelAndView对象
    public ModelAndView mav02(ModelAndView mav){
//        设置模型数据
        mav.addObject("username","modelAndView02");
//        设置视图
        mav.setViewName("success");
        return mav;
    }

    @RequestMapping("/mav03")
    public String mav03(Model model){
//        通过model对象存储数据
        model.addAttribute("username","modelAndView03");
//        直接返回视图
        return "success";
    }

    @RequestMapping("/mav04")
    public String mav04(HttpServletRequest request){
//        通过model对象存储数据
        request.setAttribute("username","request存储数据");
//        直接返回视图
        return "success";
    }

   @RequestMapping("/resp01")
    public void resp01(HttpServletResponse response) throws IOException {
        response.getWriter().write("hello springMVC");
    }

    @RequestMapping("/resp02")
    @ResponseBody   //告知springMVC框架 该方法返回值不进行页面跳转 直接响应数据
    public String resp02() {
        return "hello resp02";
    }

    @RequestMapping("/resp03")
    @ResponseBody   //告知springMVC框架 该方法返回值不进行页面跳转 直接响应数据
    public String resp03() throws JsonProcessingException {
        User user = new User();
        user.setAge(22);
        user.setName("jing");
//        使用json的转换工具将对象转为json格式字符串再返回。
        ObjectMapper om = new ObjectMapper();
        String json = om.writeValueAsString(user);
        return json;
    }

    @RequestMapping("/resp04")
    @ResponseBody   //告知springMVC框架 该方法返回值不进行页面跳转 直接响应数据
    public User resp04() {
//        直接返回对象需要在xml文件中配置 RequestMappingHandlerAdapter
        User user = new User();
        user.setAge(22);
        user.setName("张三");
        return user;
    }

    @RequestMapping("/resp05")
    @ResponseBody   //告知springMVC框架 该方法返回值不进行页面跳转 直接响应数据
    public List resp05() {
        List<String> list = new ArrayList<>();
        list.add("赵");
        list.add("钱");
        list.add("孙");
        list.add("李");
        return list;
    }
}
