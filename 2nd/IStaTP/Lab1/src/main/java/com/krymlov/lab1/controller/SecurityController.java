package com.krymlov.lab1.controller;

import com.krymlov.lab1.entity.UserEntity;
import com.krymlov.lab1.model.RolesEnum;
import com.krymlov.lab1.model.User;
import com.krymlov.lab1.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Collections;

@Controller
public class SecurityController {

    @Autowired
    private UserRepo userRepo;

    @RequestMapping("/registration")
    public String getRegisterUser(){
        return "security/register";
    }

    @PostMapping("/registration")
    public String registerUser(@Valid User user, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes){

        String referer = request.getHeader("Referer");

        if (userRepo.findByUsername(user.getUsername()) != null){
            model.addAttribute("wrongData", "Користувач з логіном " + user.getUsername() + " вже існує.");
            return "security/register";
        }

        if (user.getPassword().length() < 6){
            redirectAttributes.addFlashAttribute("wrongData", "Пароль повинен містити мінімум 6 символів");
            return "redirect:" + referer;
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(user.getUsername());
        userEntity.setPassword(user.getPassword());
        userEntity.setActive(true);
        userEntity.setRoles(Collections.singleton(RolesEnum.ROLE_USER));
        userRepo.save(userEntity);

        return "redirect:/login";
    }

    @RequestMapping("/access/denied")
    public String getAccessDeniedPage(HttpServletRequest request, RedirectAttributes redirectAttributes){

        redirectAttributes.addFlashAttribute("forbiddenAccess", "У вас немає доступу до цієї сторінки!");

        String referer = request.getHeader("Referer");
        return "redirect:"+ referer;
    }
}
