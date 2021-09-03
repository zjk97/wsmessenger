package com.rottyuniversity.wsmessenger.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReactRouteHandler {
    @GetMapping(path = {"/ui/*", "/ui"})
    public String redirectForReact() {
        return "forward:/index.html";
    }

    // maps every path that's not mapped to another controller and doesn't contain dots to index.html
//    @RequestMapping(value = "/{[path:[^\\.]*}")
//    public String redirect() {
//        return "forward:/index.html";
//    }
}
