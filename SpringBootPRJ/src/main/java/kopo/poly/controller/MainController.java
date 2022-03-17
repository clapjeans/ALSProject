package kopo.poly.controller;


import lombok.extern.slf4j.Slf4j;


import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;

import java.awt.print.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class MainController {

    //모든페이지를 메핑하는 함수


   //메인페이지
    @GetMapping(value = "home")
    public String Home(){
        log.info(getClass().getName()+"Start SearchList ");

        log.info(getClass().getName()+"End SearchList ");
        return"/home";
    }


    @GetMapping(value="loginPage")
    public String login(){
        log.info(getClass().getName()+"Start SearchList ");

        log.info(getClass().getName()+"End SearchList ");
        return"/login/login";
    }

    //메인페이지
    @GetMapping(value = "changPw")
    public String changPw(){
        log.info(getClass().getName()+"Start SearchList ");

        log.info(getClass().getName()+"End SearchList ");
        return"/login/changPw";
    }

    //메인페이지
    @GetMapping(value = "createUser")
    public String createUser(){
        log.info(getClass().getName()+"Start SearchList ");

        log.info(getClass().getName()+"End SearchList ");
        return"/login/creatUser";
    }

    //메인페이지
    @GetMapping(value = "findPw")
    public String findPW(){
        log.info(getClass().getName()+"Start SearchList ");

        log.info(getClass().getName()+"End SearchList ");
        return"/login/findPw";
    }

    //메인페이지
    @GetMapping(value = "mapInfo")
    public String mapInfo(){
        log.info(getClass().getName()+"Start SearchList ");

        log.info(getClass().getName()+"End SearchList ");
        return"/map/mapListInfo";
    }

    //메인페이지
    @GetMapping(value = "mapListInfo")
    public String mapListInfo(){
        log.info(getClass().getName()+"Start SearchList ");

        log.info(getClass().getName()+"End SearchList ");
        return"/map/mapListInfo";
    }

    //메인페이지
    @GetMapping(value = "mapSearch")
    public String mapSearch(){
        log.info(getClass().getName()+"Start SearchList ");

        log.info(getClass().getName()+"End SearchList ");
        return"/map/mapSearch";
    }

    //메인페이지
    @GetMapping(value = "picSearch")
    public String picSearch(){
        log.info(getClass().getName()+"Start SearchList ");

        log.info(getClass().getName()+"End SearchList ");
        return"/picSearch/picSearch";
    }






}
