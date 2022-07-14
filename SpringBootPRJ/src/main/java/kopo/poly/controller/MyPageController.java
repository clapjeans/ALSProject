package kopo.poly.controller;

import kopo.poly.dto.UserInfoDTO;
import kopo.poly.service.IImgService;
import kopo.poly.service.IUserService;
import kopo.poly.util.CmmUtil;
import kopo.poly.util.EncryptUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class MyPageController {

    //유저 서비스 활용
    @Resource(name = "ImgService")
    private IImgService imgService;

    //유저 서비스 활용
    @Resource(name = "UserService")
    private IUserService userService;

    //마이페이지 들어가기
    @GetMapping(value = "myPage")
    public String myPage() throws Exception {

        log.info(this.getClass().getName() + ".myPage start!");


        log.info(this.getClass().getName() + ".myPage end!");

        return "/mypage/mypage";
    }




    //유저정보 변경
    @PostMapping(value = "UpdateMyPage")
    public String UpdateMyPage(HttpServletRequest request, ModelMap model, HttpSession session) throws Exception {

        log.info(this.getClass().getName() + ".mypageEdit end");


        // 유저정보 변경 결과에 대한 메시지 전달할 변수
        String msg = "";
        String url = "";

        //회원정보 변경을 담을 변수
        UserInfoDTO pDTO = null;


        try {

            String user_email = CmmUtil.nvl((String) session.getAttribute("SS_USER_EMAIL")); //세션에있는 아이디 담아오기
            String user_name = CmmUtil.nvl(request.getParameter("user_name")); //이름




            log.info(user_name);
            log.info(user_name);




            //유저 정보를 담기위함
            pDTO = new UserInfoDTO();
            pDTO.setUser_name(user_name);
            pDTO.setUser_email(EncryptUtil.encAES128CBC(user_email)); // 이메일 AES-128-CBC 암호화
            pDTO.setUser_email(EncryptUtil.encAES128CBC(user_name)); //아이디 AES-128-CBC 암호화


            int res = userService.UpdateUserPage(pDTO);


            if (res == 1) {
                // 변경후 세션에 이름 새로답기
                session.setAttribute("SS_USER_NAME", user_name);
                msg = "회원정보가 변경되었습니다..";
                url = "/index";
            } else {
                msg = "오류로 인해  회원정보변경이 실패 하였습니다.";
                url = "/userPage";
            }

        } catch (Exception e) {
            // 저장이 실패되면 유저 보여줄 메시지
            msg = "오류로 인해 회원정보변경이 실패 하였습니다.";
            url = "/userPage";
            log.info(e.toString());
            e.printStackTrace();
        } finally {
            log.info(this.getClass().getName() + "UpdateMyPage End!!");


            // 회원가입 여부 결과 메시지 전달하기
            model.addAttribute("msg", msg);
            model.addAttribute("url", url);

            pDTO = null;
        }



        return "/redirect";
    }

    //관리자 페이지
    @GetMapping(value = "adminPage")
    public String adminPage(HttpSession session, ModelMap model) throws Exception {

        log.info(this.getClass().getName() + ".adminPage start");

        String user_type = CmmUtil.nvl((String) session.getAttribute("SS_USER_TYPE"));
        log.info("user_type : " +user_type );

        if (user_type.equals("")) {
            model.addAttribute("msg", "관리자만 접근가능합니다.");
            model.addAttribute("url", "/index");
            return "/redirect";
        }

        //관리자 아이디 제외 회원 정보가져오기
        UserInfoDTO pDTO = new UserInfoDTO();
        pDTO.setUser_email(EncryptUtil.encAES128CBC((String)session.getAttribute("SS_USER_EMAIL"))); //아이디 AES-128-CBC 암호화

        List<UserInfoDTO> rList= userService.getUserList(pDTO);

        if(rList==null) {
            rList = new ArrayList<>();
        }


        for(UserInfoDTO rDTO : rList) {
            log.info("UserInfoDTO:" +rDTO.getUser_email());
            log.info("UserInfoDTO:" +rDTO.getUser_name());
        }
        log.info(this.getClass().getName() + ".adminPage end");

        model.addAttribute("rList",rList);
        return "/mypage/adminpage";
    }
}
