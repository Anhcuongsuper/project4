package com.project_sem4.fe.controller;
import com.project_sem4.fe.entity.Account;
import com.project_sem4.fe.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.validation.Valid;

@Controller
@RequestMapping(value = "/account")
public class AccountController {

    @Autowired
    AccountService accountService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @RequestMapping(method = RequestMethod.GET, value = "/login")
    public String showLoginPage(@Valid Account account, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "login";
        }
        return "redirect:/";
    }
    @RequestMapping(method = RequestMethod.GET, value = "/create")
    public String createAccount(Model model) {
        model.addAttribute("account", new Account());
        return "register";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public String storeAccount(@Valid Account account, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        accountService.register(account);
        return "index";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{email}")
    public ResponseEntity<Account>detail(@PathVariable String email){
        Account account = accountService.getByEmail(email);
        if (account == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(account, HttpStatus.OK);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public String showListAccount(Model model) {
        model.addAttribute("list", accountService.getList(1, 10));
        return "account/list";

    }
}
