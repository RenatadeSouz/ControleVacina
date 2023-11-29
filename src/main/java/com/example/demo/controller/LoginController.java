package com.example.demo.controller;

import com.example.demo.dto.LoginDto;
import com.example.demo.services.LoginService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class LoginController {

    @Autowired
    private LoginService service;

    @PostMapping
    public String login(LoginDto loginDto, HttpSession session) {
        return service.findByAccess(loginDto,session);
    }

    @GetMapping
    public ModelAndView telaLogin(HttpSession session) {
        ModelAndView mv = new ModelAndView("login");
        String msgErro = (String) session.getAttribute("msgErro");
        mv.addObject(msgErro);
        return mv;
    }

    @GetMapping("/sair")
    public String sair() {
        return "login";
    }
}
