package com.senai.projetowebsenai.controller;

import com.senai.projetowebsenai.model.UserModel;
import com.senai.projetowebsenai.service.CookieService;
import com.senai.projetowebsenai.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

@Controller
public class LoginController {

    @Autowired
    LoginService loginService;

    @GetMapping(value = "/login")
    public String index(){
        return "login/index";
    }


//    @PostMapping(value = "/logar")
//    public String entrar(UserModel userModel, Model model, HttpServletResponse response){
//        UserModel userModel1 = loginService.logar(userModel);
//        if(userModel1 != null){
//            CookieService.setCookie(response, "usuarioID",String.valueOf(userModel1.getId()),10);
//            return "redirect:/";
//        }
//        model.addAttribute("erro", "Usuario ou senha invalidos!");
//        return "login/index";
//    }


    @GetMapping(value ="/sair")
    public String sair(HttpServletResponse response)  {
        CookieService.setCookie(response, "usuarioId", "", 0);
        return "redirect:/login";
    }

    @PostMapping(value = "/logar")
    public String entrar(UserModel userModel, Model model, String lembrar, HttpServletResponse response){
        UserModel user = loginService.logar(userModel);
        int tempoCookie;
        if(user != null){
            tempoCookie = 10; //1 hora de cookie
            if(lembrar != null){
                tempoCookie = 60 * 60 * 24 * 365;
            }
            CookieService.setCookie(response, "usuarioId",String.valueOf(user.getId()),tempoCookie );
            CookieService.setCookie(response, "nome",user.getNome(),tempoCookie );
            return "redirect:/";
        }
        model.addAttribute("erro", "Usuario ou senha invalidos!");
        return "login/index";
    }
}
