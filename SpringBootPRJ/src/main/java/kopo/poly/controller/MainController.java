package kopo.poly.controller;


import lombok.extern.slf4j.Slf4j;
<<<<<<< HEAD
<<<<<<< HEAD
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.awt.print.Pageable;

=======
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

>>>>>>> aca8c8a62c55bf03fab80311b9e0d446ec850867
=======
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

>>>>>>> aca8c8a62c55bf03fab80311b9e0d446ec850867
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

<<<<<<< HEAD
<<<<<<< HEAD

=======
=======
>>>>>>> aca8c8a62c55bf03fab80311b9e0d446ec850867
    //메인페이지
    @GetMapping(value = "search")
    public String searchList(){
        log.info(getClass().getName()+"Start SearchList ");

        log.info(getClass().getName()+"End SearchList ");
        return"/search/listPg";
    }
<<<<<<< HEAD
>>>>>>> aca8c8a62c55bf03fab80311b9e0d446ec850867
=======
>>>>>>> aca8c8a62c55bf03fab80311b9e0d446ec850867


    //메인페이지
    @GetMapping(value = "infoPg")
    public String infoPg(){
        log.info(getClass().getName()+"Start SearchList ");

        log.info(getClass().getName()+"End SearchList ");
        return"/search/infoPg";
    }


<<<<<<< HEAD
<<<<<<< HEAD

=======
>>>>>>> aca8c8a62c55bf03fab80311b9e0d446ec850867
=======
>>>>>>> aca8c8a62c55bf03fab80311b9e0d446ec850867
}
