package com.hng.stageone.controller;


import com.hng.stageone.model.ResponseModel;
import com.hng.stageone.service.HelloService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {

    @Autowired
    private HelloService helloService;

    // Method to handle HTTP GET requests at /hello
    @GetMapping("/api/hello")
    public ResponseModel hello(@RequestParam("visitor_name") String visitorName, HttpServletRequest request){
        return helloService.getGreeting(visitorName, request.getRemoteAddr());
    }
}
