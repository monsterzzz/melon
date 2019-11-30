package com.monster.melon.controller;

import com.monster.melon.util.ValidateCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
@RequestMapping("/checkcode")
public class CheckcodeController {

    @GetMapping("")
    public void getCode(HttpServletRequest request, HttpServletResponse response){
        try {
            ValidateCode validateCode = new ValidateCode(90,20,4,50);
            request.getSession().setAttribute("checkCode", validateCode.getCode());
            validateCode.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
