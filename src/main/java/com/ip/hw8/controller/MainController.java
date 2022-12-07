package com.ip.hw8.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {
    @GetMapping("/main")
    public String main(){
        return "index";
    }
    @GetMapping("/registrationNewUser")
    public String createForm() {return "user/createUser";}

//    @PostMapping("/registrationNewUser")
//    public String  createUser(@ModelAttribute("user") @Valid User user,
//                              BindingResult bindingResult,
//                              Model model) {
//        if (user.getPassword() != null && !user.getPassword().equals(user.getPassword2())) {
//            model.addAttribute("passwordDifError", "Password are different");
//            return "user/createUser";
//        }
//
//        if (bindingResult.hasErrors()) {
//            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
//            model.mergeAttributes(errors);
//            return "user/createUser";
//        } else {
//            User userAudit = userService.findByEmail(user.getEmail());
//            if (userAudit != null) {
//                model.addAttribute("userDuplicate", "User with this name already exist!!!\nTry again.");
//                return "user/createUser";
//            }
//
//            userService.createUser(user);
//            return "redirect:/login";
//        }
//    }
}

