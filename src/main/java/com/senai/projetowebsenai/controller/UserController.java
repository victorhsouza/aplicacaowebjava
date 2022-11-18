package com.senai.projetowebsenai.controller;

import com.senai.projetowebsenai.model.UserModel;
import com.senai.projetowebsenai.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String getAllUsers(Model model) {
        List<UserModel> users = userService.findAll();
        model.addAttribute("users", users);
        return "user/users";
    }

    @GetMapping(value = "/novo")
    public String formUser() {
        return "user/novo";
    }


    @PostMapping(value = "/cadastrar")
    public String insertUser(UserModel userModel) {
        userService.saveUser(userModel);
        return "redirect:/user";
    }

    @GetMapping("/excluir/{id}")
    public String deleteUser(@PathVariable Integer id) {
        userService.deleteUserById(id);
        return "redirect:/user";
    }

    @GetMapping(value = "/{id}")
    public String getUser(@PathVariable Integer id, Model model) {
        Optional<UserModel> user = userService.getUserById(id);
        // Tratamento de exceçoes - Vai tentar executar tudo que esta no try, se der algum erro pegue essa exceçao com o catch e trate
        // da maneira que quiser.
        try {
            model.addAttribute("user", user.get());
            return "user/alterar";
        } catch (Exception e) {
            return "redirect:/user";
        }
    }


    @PostMapping(value ="/alterar/{id}")
    public String updateUser(UserModel userModel, @PathVariable Integer id) {
        Optional<UserModel> user = userService.getUserById(id);
        if(user.isEmpty()){
            return "redirect:/user";
        }
        userService.saveUser(userModel);
        return "redirect:/user";
    }

}

