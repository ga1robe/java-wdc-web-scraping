package com.github.ga1robe.wdcwebscraping.controller;

import com.github.ga1robe.wdcwebscraping.service.CardsService;
import com.github.ga1robe.wdcwebscraping.model.CardContainer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDate;

@Controller
@SessionAttributes("name")
public class CardsController {

    @Autowired
    CardsService service;

    @GetMapping("/")
    public RedirectView index(ModelMap model){
        return new RedirectView("list");
    }

//    @RequestMapping(value="/list", method = RequestMethod.GET)
//    public String showList(ModelMap model){
//        return "list";
//    }

    @RequestMapping(value="/list", method = RequestMethod.GET)
    public String getList(ModelMap model){
        model.put("mainDataSPM", this.service.getMainDataSPM());
        return "list";
    }
}
