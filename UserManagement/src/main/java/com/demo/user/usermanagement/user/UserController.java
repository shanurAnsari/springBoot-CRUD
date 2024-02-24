package com.demo.user.usermanagement.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public String listUsers(Model model) {
        List<User> users = userService.listAllUsers();
        model.addAttribute("userList", users);

        return "usersPage";
    }

    @GetMapping("/users/new")
    public String newUserForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("pageTitle", "Add New User");
        return "newUserForm";
    }

    @PostMapping("/users/save")
    public String saveUser(@ModelAttribute("user") User user, RedirectAttributes ra) {
        userService.saveUser(user);
        ra.addFlashAttribute("message", "User saved successfully");
        return "redirect:/users";
    }

    @GetMapping("/users/edit/{id}")
    public String editUserForm(@PathVariable Integer id, Model model, RedirectAttributes ra)  {

        try {
            User user = userService.getUserById(id);
            model.addAttribute("user", user);
            model.addAttribute("pageTitle", "Edit user Id (" + id+")");
            return "newUserForm";
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", "User not found");
            return "redirect:/users";
        }
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUserForm(@PathVariable Integer id, Model model, RedirectAttributes ra)  {

        try {
            userService.deleteUserById(id);
            ra.addFlashAttribute("message", "User deleted successfully");
            return "redirect:/users";
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", "User not found");
            return "redirect:/users";
        }
    }

}
