package com.rottyuniversity.wsmessenger.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorHandlerController implements ErrorController {

    @Override
    @RequestMapping("/error")
    public String getErrorPath() {
        return "forward:/index.html";
    }
}