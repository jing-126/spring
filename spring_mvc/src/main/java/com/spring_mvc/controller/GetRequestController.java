package com.spring_mvc.controller;

import com.spring_mvc.domain.User;
import com.spring_mvc.domain.VO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("user")
public class GetRequestController {
    @RequestMapping("req01")
    @ResponseBody
    public void getReq01(String username, int age){
        System.out.println(username);
        System.out.println(age);
    }

    @RequestMapping("req02")
    @ResponseBody
    public void getReq02(User user){
        System.out.println(user);
    }

    @RequestMapping("req03")
    @ResponseBody
    public void getReq03(String[] hobbies){
        System.out.println(Arrays.toString(hobbies));
        System.out.println(Arrays.asList(hobbies));
    }

    @RequestMapping("req04")
    @ResponseBody
    public void getReq04(VO vo){
        System.out.println(Arrays.asList(vo));
    }

    @RequestMapping("req05")
//    @ResponseBody
    public String getReq05(@RequestBody List<User> userList) {
        System.out.println(userList);
        return "success";
    }

//    使用@RequestParam注解显示绑定参数名称
    @RequestMapping("req06")
    @ResponseBody
    public void getReq06(@RequestParam(value = "name", required = false, defaultValue = "默认值") String username){
        System.out.println(username);
    }

//    获取Restful风格参数
    @RequestMapping("req07/{username}")
    @ResponseBody
    public void getReq07(@PathVariable("username") String username){
        System.out.println(username);
    }

//    自定义转换器
    @RequestMapping("req08")
    @ResponseBody
    public void getReq08(Date date){
        System.out.println(date);
    }

//    获取请求头
    @RequestMapping("req09")
    @ResponseBody
    public void getReq09(@RequestHeader(value = "User-Agent",required = false) String user_agent){
        System.out.println(user_agent);
    }

    @RequestMapping("req11")
    @ResponseBody
    public void getReq11(@RequestHeader(value = "Cookie",required = false) String cookie){
        System.out.println(cookie);
    }

//    获取cookie注解
    @RequestMapping("req10")
    @ResponseBody
    public void getReq10(@CookieValue("JSESSIONID") String jsessionid){
        System.out.println(jsessionid);
    }

//    文件上传
    @RequestMapping("req12")
    @ResponseBody
    public void save01(String username, MultipartFile uploadFile) throws IOException {
        System.out.println(username);
//        获取文件上传名称
        String filename = uploadFile.getOriginalFilename();
        System.out.println(filename);
        uploadFile.transferTo(new File("G:\\fileupload\\" + filename));
    }

//    多文件上传 使用不同的表单项名
    @RequestMapping("req13")
    @ResponseBody
    public void save02(String username, MultipartFile uploadFile, MultipartFile uploadFile2) throws IOException {
        System.out.println(username);
        uploadFile.transferTo(new File("G:\\fileupload\\" + uploadFile.getOriginalFilename()));
        uploadFile2.transferTo(new File("G:\\fileupload\\" + uploadFile2.getOriginalFilename()));
    }

//    多文件上传 使用相同的表单项名
    @RequestMapping("req14")
    @ResponseBody
    public void save03(MultipartFile[] uploadFile) throws IOException {
        for (MultipartFile file : uploadFile) {
            String filename = file.getOriginalFilename();
            System.out.println(filename);
            file.transferTo(new File("G:\\fileupload\\" + filename));
        }
    }
}
