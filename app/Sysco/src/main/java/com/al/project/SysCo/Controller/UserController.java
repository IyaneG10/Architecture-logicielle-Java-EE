package com.al.project.SysCo.Controller;

import com.al.project.SysCo.Model.User;
import com.al.project.SysCo.Service.SecurityService;
import com.al.project.SysCo.Service.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import static com.al.project.SysCo.Model.Data.recurseKeys;


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

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Identifiant ou mot de passe incorrects");

        if (logout != null)
            model.addAttribute("message", "Déconnexion effectuée avec succès");

        return "login";
    }

    @RequestMapping(
            value = "/logTest",
            method = RequestMethod.POST,
            consumes = "application/json")
    public String process(@RequestBody String payload) throws Exception {

        JSONObject jsonObject = new JSONObject(payload);
        String username;
        String password;

        username= recurseKeys(jsonObject,"username");
        password= recurseKeys(jsonObject,"password");
        System.out.println(payload);
        System.out.println(username);
        System.out.println(password);

        return "welcome";
    }

    @GetMapping({"/", "/welcome"})
    public String welcome(Model model) {
        return "welcome";
    }

}