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

    @GetMapping("/createUserForm")
    public String createForm() {
        return "user/createUserForm";
    }

    @PostMapping("/createUser")
    public String createUser(@ModelAttribute("user") @Valid User user,
                             BindingResult bindingResult,
                             Model model) {


        if (bindingResult.hasErrors()){
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
            return "user/createUserForm";
        }else {
            User userAudit = userService.findByEmail(user.getEmail());
        if (userAudit != null) {
            model.addAttribute("userDuplicate", "User with this name already exist!!!\nTry again.");
            return "user/createUserForm";
        }

        userService.createUser(user);
        return "user/createUser";
        }
    }

    @GetMapping("/updateUserForm")
    public ModelAndView updateForm(Model model) {
        model.addAttribute("users", userService.findAll());

        return new ModelAndView("user/updateUserForm");
    }

    @PostMapping("/updateUser")
    public ModelAndView updateUser(Model model,
                                   User user) {
        model.addAttribute("user", userService.findAll());

        userService.updateUser(user);

        return new ModelAndView("user/updateUser");
    }

    @GetMapping("/deleteUserForm")
    public ModelAndView deleteForm(Model model) {
        model.addAttribute("users", userService.findAll());

        return new ModelAndView("user/deleteUserForm");
    }

    @PostMapping("/deleteUser")
    public ModelAndView deleteUser(Model model,
                                   @RequestParam("id") Long id) {
        model.addAttribute("users", userService.findAll());

        userService.deleteUser(id);

        return new ModelAndView("user/deleteUser");
    }

}
