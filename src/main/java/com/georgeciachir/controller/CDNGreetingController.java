package com.georgeciachir.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/cdn")
public class CDNGreetingController {

    private static final Logger LOG = LoggerFactory.getLogger(CDNGreetingController.class);

    @GetMapping(value = "/hello")
    public String sayHello(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        if (name == null) {
            name = "World";
        }
        LOG.info("Greeting [{}]", name);
        model.addAttribute("name", name);
        return "greeting";
    }
}
