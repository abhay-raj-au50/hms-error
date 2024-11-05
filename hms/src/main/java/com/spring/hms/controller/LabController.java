package com.spring.hms.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import com.spring.hms.model.Lab;
import com.spring.hms.service.LabService;
import com.spring.hms.exception.InvalidCredentialsException;

@Controller
public class LabController {
    @Autowired
    private LabService labService;

    @GetMapping("/")
    public String showLogin() {
        return "login";
    }

    @PostMapping("/login-form")
    public String handleLogin(HttpServletRequest req, HttpSession session) {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        try {
            boolean isValidUser = labService.verifyLogin(username, password);
            if (isValidUser) {
                session.setAttribute("username", username);
                List<Lab> labs = labService.findAll();
                req.setAttribute("listLabs", labs);
                return "lab_dashboard";
            } else {
                throw new InvalidCredentialsException("Invalid username or password");
            }
        } catch (InvalidCredentialsException e) {
            req.setAttribute("msg", e.getMessage());
            return "login";
        }
    }

    @GetMapping("/lab-dashboard")
    public String goToLabDashboard(HttpServletRequest req, HttpSession session) {
        List<Lab> labs = labService.findAll();
        req.setAttribute("listLabs", labs);
        return "lab_dashboard";
    }

    @PostMapping("/add-lab")
    public String addLab(HttpServletRequest req) {
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        Lab lab = new Lab();
        lab.setName(name);
        lab.setDescription(description);
        labService.save(lab);
        return "redirect:/lab-dashboard";
    }

    @GetMapping("/delete-lab")
    public String deleteLab(@RequestParam("id") Long id) {
        labService.delete(id);
        return "redirect:/lab-dashboard";
    }

    @GetMapping("/soft-delete-lab")
    public String softDeleteLab(@RequestParam("id") Long id) {
        labService.softDelete(id);
        return "redirect:/lab-dashboard";
    }
}
