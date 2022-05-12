package com.example.lastfresh.controller.main;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/*메인페이지 및 GNB로 페이지이동 관련 전반적 페이지 이동*/

@Controller
@Slf4j
@RequestMapping("/main/*")
public class MainPageController {


    @GetMapping("/main")
    public void main(){}

    @GetMapping("/mainTerm")
    public void mainTerm(){}


    @GetMapping("/mainHowToUser")
    public void mainHowToUser(){}




}
