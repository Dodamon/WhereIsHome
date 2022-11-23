//package com.ssafy.happyhouse.error.controller;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//@Controller
//@RequestMapping("/error-page")
//public class ErrorPageController {
//
//    @RequestMapping("/400")
//    public String errorPage404(HttpServletRequest request, HttpServletResponse response) {
//        log.error("errorPage 400");
//        System.out.println("Error400 Controller");
//        return "error-page/400";
//    }
//
//    @RequestMapping("/500")
//    public String errorPage500(HttpServletRequest request, HttpServletResponse response) {
//        log.error("errorPage 500");
//        System.out.println("Error500 Controller");
//        return "error-page/500";
//    }
//
//}