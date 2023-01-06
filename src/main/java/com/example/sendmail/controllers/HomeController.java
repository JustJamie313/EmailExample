package com.example.sendmail.controllers;

import com.example.sendmail.dto.Form;
import com.example.sendmail.services.EmailService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
@Controller
public class HomeController {
    @Autowired
    EmailService emailService;

    @GetMapping("/")
    public String displayRegistrationForm(Model model) {
        model.addAttribute(new Form());
        return "index";
    }

    @PostMapping("/")
    public String processRegistrationForm(@ModelAttribute @Valid Form form, Errors errors, Model model) throws MessagingException {
        if (errors.hasErrors()){
            model.addAttribute(form);
            return "index";
        }
        String emailString= """
                  <!DOCTYPE html>
                  <html lang="en" xmlns:th="https://www.thymeleaf.org">
                  <head>
                      <meta charset="utf-8"/>
                      <title></title>
                  </head>
                  <body>
                  <p>This is a test email.</p>
                  </body>
                  </html>
                """;
        emailService.sendMail(form.getEmail(),emailString,"Test E-mail");
        return "return";
    }
}
