package com.al.project.SysCo.Controller;

import com.al.project.SysCo.Model.User;
import com.al.project.SysCo.Service.SecurityService;
import com.al.project.SysCo.Service.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.al.project.SysCo.Model.Data.recurseKeys;


@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

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
    public ResponseEntity<String> process(@RequestBody String payload) throws Exception {

        JSONObject jsonObject = new JSONObject(payload);
        String username;
        String password;

        username= recurseKeys(jsonObject,"username");
        password= recurseKeys(jsonObject,"password");
        System.out.println(payload);
        //System.out.println(username);
        //System.out.println(password);
        securityService.autoLogin(username, password);

        User userToLog=  userService.findByUsername(username);
        //userToLog.setPassword(bCryptPasswordEncoder.encode(userToLog.getPassword()));
        System.out.println(password);
        if (userService.findByUsername(username)== null)
        {
            return new ResponseEntity<String>("{\"Reponse\": \"Utilisateur inconnu\"}",  HttpStatus.FORBIDDEN);
        }
        else {
            //System.out.println("encoded password: "+ userToLog.getPassword());
            if (userToLog.getPassword().equals(password)) {
                return new ResponseEntity<String>("{\"Reponse\": \"Ca marche\"}",  HttpStatus.OK);
            }
            else {
                return new ResponseEntity<String>("{\"Reponse\": \"Mauvais mot de passe\"}",  HttpStatus.FORBIDDEN);
            }
        }


        //AuthenticationManagerBuilder auth = new AuthenticationManagerBuilder();
        //auth.inMemoryAuthentication();
        //System.out.println(auth);
        //return "welcome";
    }


    @GetMapping({"/", "/welcome"})
    public String welcome(Model model) {
        return "welcome";
    }

}