package com.al.project.SysCo.Controller;

import com.al.project.SysCo.Model.User;
import com.al.project.SysCo.Service.SecurityService;
import com.al.project.SysCo.Service.UserService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;


@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }


        userService.save(userForm);

        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/welcome";
    }

    @PostMapping("/login/")// /room{room_id}/{measureName}
    public String loginMobile(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) throws SQLException, JSONException {

        securityService.autoLogin(username, password);

        return "{\"connection\":true}";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Identifiant ou mot de passe incorrects");

        if (logout != null)
            model.addAttribute("message", "Déconnexion effectuée avec succès");

        return "login";
    }

    @GetMapping({"/", "/welcome"})
    public String welcome(Model model) {
        return "welcome";
    }


    @GetMapping("/products")
    public String list(){
            return "products";
        }

}