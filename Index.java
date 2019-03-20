/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controllers;

import com.dao.SignUpDao;
import com.models.SignUpInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Sajid
 */
@Controller
public class Index {

    @Autowired
    private SignUpDao signUpDao;

    @RequestMapping(value = "about", method = RequestMethod.GET)
    public String doGETABOUT(Model model) {
        model.addAttribute("pageinfo", "about");
        return "about";
    }

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String doGETINDEX(Model model) {
        model.addAttribute("pageinfo", "index");
        model.addAttribute("noLogin", "");
        model.addAttribute("newFarmer", new SignUpInfo());
        return "index";
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String doGETLOGIN(Model model) {
        model.addAttribute("pageinfo", "index");
        model.addAttribute("newFarmer", new SignUpInfo());
        return "index";
    }

    @RequestMapping(value = "contact", method = RequestMethod.GET)
    public String doGETCONTACT(Model model) {
        model.addAttribute("pageinfo", "contact");
        return "contact";
    }

    @RequestMapping(value = "adminPanel", method = RequestMethod.GET)
    public String doGETADMINPANEL(Model model) {
        model.addAttribute("pageinfo", "admin");
        model.addAttribute("farmerInfoInput", " ");
        return "adminPanel";
    }

    @RequestMapping(value = "signup", method = RequestMethod.POST)
    public String doPOST1(@ModelAttribute("newFarmer") SignUpInfo user, Model model,
            BindingResult result, RedirectAttributes ra) {

        user.setAuthority("ROLE_farmer");

        try {
            signUpDao.createUser(user);
        } catch (DuplicateKeyException ex) {
            result.rejectValue("username", "the user already exists");
        }

        model.addAttribute("pageinfo", "index");
        model.addAttribute("noLogin", "");
        model.addAttribute("newFarmer", new SignUpInfo());

        return "redirect:/index";
    }

    @RequestMapping(value = "services", method = RequestMethod.GET)
    public String doGETSERVICES(RedirectAttributes ra) {
        return "redirect:/index";
    }

}
