package org.launchcode.voterengagement.controllers;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "voter")
public class VoterController {

    @RequestMapping(value = "")
    public String index(Model model, HttpServletRequest request, HttpServletResponse response) {

        model.addAttribute("title", "Voter Engagement App");
        return "voter/index";
    }

}
