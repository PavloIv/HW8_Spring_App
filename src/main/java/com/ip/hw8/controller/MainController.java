package com.ip.hw8.controller;

import com.ip.hw8.entity.User;
import com.ip.hw8.repository.UserRepository;
import com.ip.hw8.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final UserRepository userRepository;
    private final UserService userService;


    @GetMapping("/main")
    public ModelAndView main(){
        return new ModelAndView("index");
    }

    @GetMapping("/registrationNewUser")
    public ModelAndView createForm() {

        return new ModelAndView("registration");
    }

    @PostMapping("/registrationNewUser")
    public String  createUser(User user,
                              Model model) {
        User userAudit = userRepository.findByEmail(user.getEmail());
        if (userAudit != null) {
            model.addAttribute("message","User with this name already exist!!!\n" +
                    "Try again.");
            return "registration";
        }else {
        userService.createUser(user);
        }
        return "redirect:/login";
    }
}

