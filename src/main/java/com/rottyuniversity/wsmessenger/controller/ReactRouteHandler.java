package com.rottyuniversity.wsmessenger.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReactRouteHandler {
    @GetMapping(path = {"/ui/*", "/ui"})
    public String redirectForReact() {
        return "forward:/index.html";
    }
}
