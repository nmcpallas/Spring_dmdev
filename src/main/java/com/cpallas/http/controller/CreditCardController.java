package com.cpallas.http.controller;

import com.cpallas.dto.CreditCardCreateEditDto;
import com.cpallas.service.CreditCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@RequestMapping("/cards")
@RequiredArgsConstructor
@Controller
public class CreditCardController {

    private final CreditCardService service;

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("cards", service.findAll());
        return "card/cards";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Integer id, Model model) {
        return service.findById(id).map(card -> {
            model.addAttribute("card", card);
            return "card/card";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/registration")
    public String registration(Model model, @ModelAttribute("user") CreditCardCreateEditDto card) {
        model.addAttribute("card", card);

        return "user/registration";
    }

    @PostMapping
    public String create(@ModelAttribute CreditCardCreateEditDto card) {
        return "redirect:/cards/" + service.create(card).getId();
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") Integer id, @RequestBody CreditCardCreateEditDto card) {
        return service.update(id, card)
                .map(it -> "redirect:/cards/{id}")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{id}/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String delete(@PathVariable("id") Integer id) {
        if (!service.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return "redirect:/cards";
    }
}
