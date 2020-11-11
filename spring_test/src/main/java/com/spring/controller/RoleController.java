package com.spring.controller;

import com.spring.domain.Role;
import com.spring.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @RequestMapping("list")
    public ModelAndView list(){
        ModelAndView modelAndView = new ModelAndView();
        List<Role> list = roleService.list();
        modelAndView.addObject("roleList",list);
        modelAndView.setViewName("role-list");
        return modelAndView;
    }

    @RequestMapping("save")
    public String save(String roleName, String roleDesc){
        roleService.save(roleName,roleDesc);
        return "redirect:/role/list";
    }
}
