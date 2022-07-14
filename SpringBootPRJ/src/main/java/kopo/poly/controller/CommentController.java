package kopo.poly.controller;

import kopo.poly.dto.CommetDTO;
import kopo.poly.service.ICommentService;
import kopo.poly.util.CmmUtil;
import kopo.poly.util.DateUtil;
import kopo.poly.util.EncryptUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public  class CommentController {

    //댓글 가져오기
    @Resource(name = "CommentService")
    private ICommentService commentService;


    // 댓글 저장하기
    @ResponseBody
    @PostMapping(value = "insertCommentAJAX")
    public int insertCommentAJAX(HttpServletRequest request, HttpSession session) throws Exception {

        log.info(this.getClass().getName() + ".insertCommentAJAX start");

        //시퀀스 대신 랜덤 숫자 만들어서 사용
        int num = (int)Math.floor(Math.random()*10000);

       int res = 0;
       
        try {
            // 이메일 AES-128-CBC 암호화
            String user_name = CmmUtil.nvl((String) session.getAttribute("SS_USER_NAME")); //이름
            String user_email = CmmUtil.nvl((String) session.getAttribute("SS_USER_EMAIL")); //이메일
            String comment = CmmUtil.nvl(request.getParameter("comment"));
            String seq = CmmUtil.nvl(request.getParameter("seq"));


            log.info("comment : " + comment);
            log.info("user_email : " + user_email);

            CommetDTO pDTO = new CommetDTO();
            pDTO.setUser_name(user_name); //사용자이름
            pDTO.setUser_email(EncryptUtil.encAES128CBC(user_email)); //사용자 이메일 인코딩해서 넣기
            pDTO.setReg_dt(DateUtil.getDateTime("yyyy-MM-dd-hh:mm:ss")); //저장날짜
            pDTO.setComments(comment); //댓글 내용
            pDTO.setBoardseq(seq); //게시물 시퀀스 값
            pDTO.setCommentseq(String.valueOf(num)); //구분할 댓글 시퀀스값


           res = commentService.insertComment(pDTO);

        } catch (Exception e) {
      
            log.info(e.toString());
            e.printStackTrace();
        }

        log.info(this.getClass().getName() + ".insertCommentAJAX end");

        log.info("res: "+ res);  //성공했는지 안했는지 res 값으로 구분 성공하면 1이 뜸
        return res;

    }


    //아작스로 댓글 가져오기
    @RequestMapping("/mCommentGetList") //댓글 리스트
    @ResponseBody
    private List<CommetDTO> mCommentGetList(ModelMap model,HttpServletRequest request) throws Exception{


        log.info(getClass().getName() + "Start mCommentGetList ");

        String seq = CmmUtil.nvl(request.getParameter("seq"));

        log.info("seq :"+ seq); //댓글을 구분하는 구분자 seq가 잘 출력되는지 확인


        return commentService.commentgetList(seq);
    }




    // 댓글 삭제하기
    @ResponseBody
    @PostMapping(value = "deleteCommentAJAX")
    public int deleteCommentAJAX(HttpServletRequest request) throws Exception {

        log.info(this.getClass().getName() + ".deleteCommentAJAX start");


        int res = 0;

        try {

            String comment = CmmUtil.nvl(request.getParameter("comment"));
            String seq = CmmUtil.nvl(request.getParameter("seq"));


            log.info("comment : " + comment);
            log.info("seq : " + seq);

            CommetDTO pDTO = new CommetDTO();
            pDTO.setComments(comment);
            pDTO.setBoardseq(seq);

            res = commentService.deleteComment(pDTO);

        } catch (Exception e) {

            log.info(e.toString());
            e.printStackTrace();
        }

        log.info(this.getClass().getName() + ".deleteCommentAJAX end");

        log.info("res: " + res);

        return res; //updateComment
    }

    // 아작스로 댓글 수정하기
    @ResponseBody
    @PostMapping(value = "/updateCommentAJAX")
    public int editCommentAJAX(HttpServletRequest request) throws Exception {

        log.info(this.getClass().getName() + ".updateCommentAJAX start");

        int res = 0;

        try {

            String updateComment = CmmUtil.nvl(request.getParameter("updateComment"));
            String comseq = CmmUtil.nvl(request.getParameter("comseq"));


            log.info("updateComment : " + updateComment);
            log.info("comseq : " + comseq);

            CommetDTO pDTO = new CommetDTO();
            pDTO.setComments(updateComment); //업데이트하는 댓글 내용
            pDTO.setCommentseq(comseq); //댓글 시퀀스값
            pDTO.setReg_dt(DateUtil.getDateTime("yyyy-MM-dd-hh:mm:ss")); //저장날짜

            res = commentService.updateComment(pDTO);

        } catch (Exception e) {

            log.info(e.toString());
            e.printStackTrace();
        }

        log.info(this.getClass().getName() + ".updateCommentAJAX end");

        log.info("res: " + res); //res가 1 이면 수정완료 res 가 1이 아니면 수정불가

        return res;
    }
}
