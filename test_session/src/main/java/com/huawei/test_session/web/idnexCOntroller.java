package com.huawei.test_session.web;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class idnexCOntroller {

    @RequestMapping("/hello/{userName}/{userId}")
    public String hello(HttpServletRequest request, HttpServletResponse response,
                        @PathVariable("userName") String userName,@PathVariable("userId") String userId) {
        System.out.println(userId+"  " + userName);
        String name = (String) request.getSession().getAttribute("userName");
        System.out.println(name);
        System.out.println(request.getSession()+"--------");
        System.out.println(request.getSession(false)+"--------");
        return "userName";
    }
}
