package com.usermanagement.controller;

import com.usermanagement.model.user;
import com.usermanagement.service.userNotFoundException;
import com.usermanagement.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class userController {
    @Autowired private userService service;

    @GetMapping("/users")
    public String showUserList(Model model){
        List<user> listUsers = service.listAll();
        model.addAttribute("listUsers", listUsers);

        return "users";
    }

    @GetMapping("/users/new")
    public String showNew(Model model){
        model.addAttribute("user", new user());
        model.addAttribute("pageTitle","Add new user");
        return "users_form";
    }

    @PostMapping("/users/save")
    public String saveUser(user user, RedirectAttributes ra){
        service.save(user);
        ra.addFlashAttribute("message","The User Has Been Saved Successfully");
        return "redirect:/users";
    }

    @GetMapping("/users/edit/{id}")
    public String showEditForm(@PathVariable("id")Integer id, Model model, RedirectAttributes ra){
        try{
        user user = service.getUser(id);
        model.addAttribute("user", user);
        model.addAttribute("pageTitle","Edit user (ID:" + id + ")");
        return "users_form";
        }catch(userNotFoundException e){
            ra.addFlashAttribute("message","The User Has Been Saved Successfully");
            return "redirect:/users";
        }
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes ra){
        try{
            service.deleteUser(id);
        }catch (userNotFoundException e){
            ra.addFlashAttribute("message",e.getMessage());
        }
        return "redirect:/users";
    }
}
