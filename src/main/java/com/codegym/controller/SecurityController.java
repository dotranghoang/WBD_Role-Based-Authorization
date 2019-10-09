package com.codegym.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SecurityController {
    private String getPrincipal(){
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(principal instanceof UserDetails){
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }

    @GetMapping(value = {"/","/home"})
    public String homePage(Model model){
        model.addAttribute("user",getPrincipal());

        return "/welcome";
    }

    @GetMapping("/admin")
    public String adminPage(ModelMap map){
        map.addAttribute("user",getPrincipal());

        return "/admin";
    }

    @RequestMapping(value = "/Access_Denied",method = RequestMethod.GET)
    public String accessDeniedPage(ModelMap modelMap){
        modelMap.addAttribute("user",getPrincipal());

        return "/accessDenied";
    }

    @GetMapping("/dba")
    public String dbaPage(ModelMap modelMap){
        modelMap.addAttribute("user",getPrincipal());
        return "/dba";
    }
}
