package com.ip.hw8.controller;

import com.ip.hw8.entity.Role;
import com.ip.hw8.entity.User;
import com.ip.hw8.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @GetMapping("/allUser")
    public ModelAndView allUser(Model model) {

        model.addAttribute("users", userRepository.findAll());

        return new ModelAndView("user/allUser");
    }

    @GetMapping("/createUserForm")
    public ModelAndView createForm() {

        return new ModelAndView("user/createUserForm");
    }

    @PostMapping("/createUser")
    public ModelAndView createUser(@RequestParam(name = "email") String email,
                                   @RequestParam(name = "password") String password,
                                   @RequestParam(name = "firstName") String firstName,
                                   @RequestParam(name = "lastName") String lastName,
                                   Map<String,Object> model) {
        User userAudit = userRepository.findByEmailUser(email);
        if (userAudit != null) {
            model.put("message","User with this name already exist!!!\nTry again.");
            return new ModelAndView("user/createUserForm");
        }

        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setActive(true);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setRoles(Collections.singletonList(new Role("ROLE_USER")));
        userRepository.save(user);

        return new ModelAndView("user/createUser");
    }

    @GetMapping("/updateUserForm")
    public ModelAndView updateForm(Model model) {
        model.addAttribute("users", userRepository.findAll());

        return new ModelAndView("user/updateUserForm");
    }

    @PostMapping("/updateUser")
    public ModelAndView updateUser(Model model,
                                   @RequestParam(name = "id") Long id,
                                   @RequestParam(name = "email") String email,
                                   @RequestParam(name = "password") String password,
                                   @RequestParam(name = "firstName") String firstName,
                                   @RequestParam(name = "lastName") String lastName ) {

        model.addAttribute("user", userRepository.findAll());

        User user = new User();
        user.setId(id);
        user.setEmail(email);
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(password));
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setRoles(Collections.singletonList(new Role("ROLE_USER")));

        userRepository.save(user);

        return new ModelAndView("user/updateUser");
    }

    @GetMapping("/deleteUserForm")
    public ModelAndView deleteForm(Model model) {
        model.addAttribute("users", userRepository.findAll());

        return new ModelAndView("user/deleteUserForm");
    }

    @PostMapping("/deleteUser")
    public ModelAndView deleteUser(Model model,
                                   @RequestParam("id") Long id) {
        model.addAttribute("users", userRepository.findAll());
        userRepository.deleteById(id);

        return new ModelAndView("user/deleteUser");
    }

}
