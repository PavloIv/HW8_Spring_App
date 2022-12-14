package com.ip.hw8.controller;

import com.ip.hw8.entity.User;
import com.ip.hw8.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/allUser")
    public ModelAndView allUser(Model model) {
        model.addAttribute("users", userService.findAll());
        return new ModelAndView("user/allUser");
    }

    @GetMapping("/createUser")
    public String createForm() {
        return "user/createUser";
    }

    @PostMapping("/createUser")
    public String createUser(@RequestParam(name = "password2") String password2,
                             @ModelAttribute("user") @Valid User user,
                             BindingResult bindingResult,
                             Model model) {
        if (user.getPassword() != null && !user.getPassword().equals(password2)) {
            model.addAttribute("passwordDifError", "Password are different");
            return "user/createUser";
        }
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
            return "user/createUser";
        }
        userService.createUser(user);
        model.addAttribute("userCreate", "User create successful");
        return "user/createUser";
    }

    @GetMapping("/updateUser")
    public String updateForm(Model model) {
        model.addAttribute("users", userService.findAll());
        return "user/updateUser";
    }

    @PostMapping("/updateUser")
    public String updateUser(@ModelAttribute("user") @Valid User user,
                             BindingResult bindingResult,
                             Model model) {
        model.addAttribute("users", userService.findAll());
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
            return "user/updateUser";
        }
        userService.updateUser(user);
        model.addAttribute("userUpdate", "User update successful");
        return "user/updateUser";
    }

    @GetMapping("/deleteUser")
    public String deleteForm(Model model) {
        model.addAttribute("users", userService.findAll());
        return "user/deleteUser";
    }

    @PostMapping("/deleteUser")
    public String deleteUser(Model model,
                             @RequestParam("id") Long id) {
        model.addAttribute("users", userService.findAll());
        userService.deleteUser(id);
        model.addAttribute("userDelete", "User delete successful");
        return "redirect:deleteUser";
    }
}
