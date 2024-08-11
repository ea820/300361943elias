package com.example.salesexam.controller;

import jakarta.validation.Valid;

import com.example.salesexam.model.Salesman;
import com.example.salesexam.service.SalesmanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class SalesmanController {

    @Autowired
    private SalesmanService service;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("listSalesmen", service.listAll());
        model.addAttribute("salesman", new Salesman());
        return "sales";
    }


    @PostMapping("/save")
    public String saveSalesman(@Valid @ModelAttribute("salesman") Salesman salesman, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("listSalesmen", service.listAll());
            return "sales";
        }
        service.save(salesman); // This should handle both new entries and updates
        return "redirect:/";
    }




    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Salesman salesman = service.get(id);
        model.addAttribute("salesman", salesman);
        return "edit_salesman";
    }

    @GetMapping("/delete/{id}")
    public String deleteSalesman(@PathVariable("id") Long id) {
        service.delete(id);
        return "redirect:/";
    }
}
