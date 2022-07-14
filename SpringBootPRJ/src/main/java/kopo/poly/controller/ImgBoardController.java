package kopo.poly.controller;


import kopo.poly.dto.BoardDTO;
import kopo.poly.dto.CommetDTO;
import kopo.poly.dto.ImgDTO;
import kopo.poly.service.IBoardService;
import kopo.poly.service.ICommentService;
import kopo.poly.service.IImgService;
import kopo.poly.service.IS3Service;
import kopo.poly.util.CmmUtil;
import kopo.poly.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class ImgBoardController extends AbstractImgUpload {

    /*

     * 비즈니스 로직(중요 로직을 수행하기 위해 사용되는 서비스를 메모리에 적재(싱글톤패턴 적용됨)
     */
    @Resource(name = "BoardService")
    private IBoardService boardService;

    @Resource(name = "ImgService")
    private IImgService imgService;

    //댓글 가져오기
    @Resource(name = "CommentService")
    private ICommentService commentService;

    //이미지 서비스 활용
    @Resource(name = "S3Service")
    private IS3Service s3Service;


    // 사진 게시판 리스트 가져오기
    @GetMapping(value = "boardList")
    public String boardList(HttpSession session, ModelMap model,HttpServletRequest request) throws Exception {
        log.info(getClass().getName() + "Start SearchList ");

        // 공지사항 리스트 가져오기
        List<Document> rList = boardService.getNoticeList();

        if (rList == null) {
            rList = new ArrayList<>();

        }

        log.info("rList : "+rList);


        // 조회된 리스트 결과값 넣어주기
        model.addAttribute("rList", rList);

        log.info(getClass().getName() + "End SearchList ");
        return "/board/boardList";
    }

    // 사진 등록게시판
    @GetMapping(value = "boardReg")
    public String boardReg(HttpSession session) {
        log.info(getClass().getName() + "Start SearchList ");


        log.info(getClass().getName() + "End SearchList ");
        return "/board/boardReg";
    }

    // 사진게시판 상세정보
    @GetMapping(value = "boardInfo")
    public String boardInfo(HttpSession session, HttpServletRequest request, ModelMap model) {
        log.info(getClass().getName() + "boardInfo Start ");

        String msg = "";

        try {
            /*
             * 게시판 글 등록되기 위해 사용되는 form객체의 하위 input 객체 등을 받아오기 위해 사용함
             */
            String seq = CmmUtil.nvl(request.getParameter("seq")); // 공지글번호(PK)

            /*
             * ####################################################################################
             * 반드시, 값을 받았으면, 꼭 로그를 찍어서 값이 제대로 들어오는지 파악해야함 반드시 작성할 것
             * ####################################################################################
             */
            log.info("seq : " + seq);

            /*
             * 값 전달은 반드시 DTO 객체를 이용해서 처리함 전달 받은 값을 DTO 객체에 넣는다.
             */
            BoardDTO pDTO = new BoardDTO();
            pDTO.setSeq(seq);

            // 게시물 상세정보 가져오기
            BoardDTO rDTO = boardService.getNoticeInfo(pDTO); //게시판 상세정보
            ImgDTO IDTO = imgService.getFilePath(seq);  //사진
            List<CommetDTO> rList = commentService.commentgetList(seq);  //댓글


            log.info("rList"+rList);

            if (rDTO == null) {
                rDTO = new BoardDTO();

            }
            if (IDTO == null) {
                IDTO = new ImgDTO();

            }
            log.info(IDTO.getSave_file_name());
            log.info(IDTO.getSave_sfile_name());

            log.info("getNoticeInfo success!!!");

            // 조회된 리스트 결과값 넣어주기
            model.addAttribute("seq",seq);
            model.addAttribute("rDTO", rDTO);
            model.addAttribute("IDTO", IDTO);
            model.addAttribute("rList", rList);


        } catch (Exception e) {

            // 저장이 실패되면 사용자에게 보여줄 메시지
            msg = "실패하였습니다. : " + e.getMessage();
            log.info(e.toString());
            e.printStackTrace();

        } finally {
            log.info(this.getClass().getName() + ".boardInfo end!");

            // 결과 메시지 전달하기
            model.addAttribute("msg", msg);

        }


        return "/board/boardInfo";
    }


    // 게시판 정보 등록하기
    @PostMapping(value = "boardInsert")
    public String boardInsert(HttpSession session, HttpServletRequest request, ModelMap model,
                              @RequestParam(value = "pro-image", required = false) MultipartFile mf) throws Exception {

        log.info(getClass().getName() + ". boardInsert Start  ");

        log.info("mf" +mf);

        String msg = "";


        String Originalname = mf.getOriginalFilename();

        log.info("파일이 옵니까?" + Originalname);



        //시퀀스 대신 랜덤 숫자 만들어서 사용
        String num = String.valueOf(Math.floor(Math.random()*10000));


        if (mf != null) {
           int result = super.ImgUpload(session, model, mf,num);
           log.info("이미지 사진등록이 완료되어있으면 1입니다." + result);
        }


        try {
            /*
             * 게시판 글 등록되기 위해 사용되는 form객체의 하위 input 객체 등을 받아오기 위해 사용함
             */
            String user_name = CmmUtil.nvl((String) session.getAttribute("SS_USER_NAME")); //이름
            String user_email = CmmUtil.nvl((String) session.getAttribute("SS_USER_EMAIL")); //이메일
            String title = CmmUtil.nvl(request.getParameter("title")); // 제목
            String contents = CmmUtil.nvl(request.getParameter("contents")); // 내용
            String category = CmmUtil.nvl(request.getParameter("category")); // 내용


            /*
             * ####################################################################################
             * 반드시, 값을 받았으면, 꼭 로그를 찍어서 값이 제대로 들어오는지 파악해야함 반드시 작성할 것
             * ####################################################################################
             */
            log.info("title : " + title);
            log.info("contents : " + contents);
            log.info("category : " + category);
            log.info("user_email : " + user_email);

            BoardDTO pDTO = new BoardDTO();

            pDTO.setTitle(title);
            pDTO.setContents(contents);
            pDTO.setCategory(category);
            pDTO.setUser_name(user_name);
            pDTO.setUser_email(user_email);
            pDTO.setReg_dt(DateUtil.getDateTime("yyyy-MM-dd-hh:mm:ss")); //저장날짜
            pDTO.setSeq(num);

            /*
             * 게시글 등록하기위한 비즈니스 로직을 호출
             */
            int res = boardService.InsertNoticeInfo(pDTO);

            log.info(this.getClass().getName() + res);

            // 저장이 완료되면 사용자에게 보여줄 메시지
            msg = "등록되었습니다.";


        } catch (Exception e) {

            // 저장이 실패되면 사용자에게 보여줄 메시지
            msg = "실패하였습니다. : " + e.getMessage();
            log.info(e.toString());
            e.printStackTrace();

        } finally {
            log.info(this.getClass().getName() + ".NoticeInsert end!");

            // 결과 메시지 전달하기
            model.addAttribute("msg", msg);
            model.addAttribute("url", "/boardList");

        }

        log.info(getClass().getName() + ". boardInsert End ");
        return "/redirect";

    }

    /**
     * 게시판 수정 보기
     */
    @GetMapping(value = "boardEditInfo")
    public String NoticeEditInfo(HttpServletRequest request, ModelMap model) {

        log.info(this.getClass().getName() + ".boardEditInfo start!");
        String msg = "";

        try {
            /*
             * 게시판 글 등록되기 위해 사용되는 form객체의 하위 input 객체 등을 받아오기 위해 사용함
             */
            String seq = CmmUtil.nvl(request.getParameter("seq")); // 공지글번호(PK)

            /*
             * ####################################################################################
             * 반드시, 값을 받았으면, 꼭 로그를 찍어서 값이 제대로 들어오는지 파악해야함 반드시 작성할 것
             * ####################################################################################
             */
            log.info("seq : " + seq);

            /*
             * 값 전달은 반드시 DTO 객체를 이용해서 처리함 전달 받은 값을 DTO 객체에 넣는다.
             */
            BoardDTO pDTO = new BoardDTO();
            pDTO.setSeq(seq);

            // 게시물 상세정보 가져오기
            BoardDTO rDTO = boardService.getNoticeInfo(pDTO); //게시판정보
            ImgDTO IDTO = imgService.getFilePath(seq); //이미지 파일정보


            if (rDTO == null) {
                rDTO = new BoardDTO();

            }
            if (IDTO == null) {
                IDTO = new ImgDTO();

            }

            log.info("getNoticeInfo success!!!");

            // 조회된 리스트 결과값 넣어주기
            model.addAttribute("rDTO", rDTO);
            model.addAttribute("IDTO", IDTO);


        } catch (Exception e) {

            // 저장이 실패되면 사용자에게 보여줄 메시지
            msg = "실패하였습니다. : " + e.getMessage();
            log.info(e.toString());
            e.printStackTrace();

        } finally {
            log.info(this.getClass().getName() + ".boardInfo end!");

            // 결과 메시지 전달하기
            model.addAttribute("msg", msg);

        }

        log.info(this.getClass().getName() + ".boardEditInfo end!");


        log.info(getClass().getName() + "End boardEditInfo ");

        return "/board/boardEditInfo";
    }

    //게시물 수정
    @PostMapping(value = "boardUpdate")
    public String boardUpdate(HttpSession session, HttpServletRequest request, ModelMap model,
                              @RequestParam(value = "pro-image", required = false) MultipartFile mf) throws Exception {
        log.info(getClass().getName() + ". boardUpdate Start  ");


        log.info("mf "+mf);



        String seq = CmmUtil.nvl(request.getParameter("seq")); // 글번호(PK)

        if (mf!=null) {

            log.info("사진등록을 시작합니다.");
            int result = super.ImgUpdateUpload(session, model, mf,seq);
            log.info("이미지 사진등록이 완료되어있으면 1입니다." + result);
        }


        String msg = "";

        try {
            String user_name = CmmUtil.nvl((String) session.getAttribute("SS_USER_NAME")); //이름
            String user_email = CmmUtil.nvl((String) session.getAttribute("SS_USER_EMAIL")); //이메일
            String title = CmmUtil.nvl(request.getParameter("title")); // 제목
            String contents = CmmUtil.nvl(request.getParameter("contents")); // 내용
            String category = CmmUtil.nvl(request.getParameter("category")); // 카테고리


            log.info("seq : " + seq);
            log.info("title : " + title);
            log.info("contents : " + contents);
            log.info("category : " + category);

            BoardDTO pDTO = new BoardDTO();
            pDTO.setSeq(seq);
            pDTO.setTitle(title);
            pDTO.setUser_name(user_name);
            pDTO.setUser_email(user_email);
            pDTO.setChg_dt(DateUtil.getDateTime("yyyy-MM-dd-hh:mm:ss")); //저장날짜
            pDTO.setContents(contents);
            pDTO.setCategory(category);

            // 게시글 수정하기 DB
            int res = boardService.updateNoticeInfo(pDTO);

            log.info("Res : "+ res );

            msg = "수정되었습니다.";

        } catch (Exception e) {
            msg = "실패하였습니다. : " + e.getMessage();
            log.info(e.toString());
            e.printStackTrace();

        } finally {
            log.info(this.getClass().getName() + ".NoticeUpdate end!");

            // 결과 메시지 전달하기
            model.addAttribute("msg", msg);
            model.addAttribute("url","/boardList");

        }

        return "/redirect";

    }

    /**
     * 게시판 글 삭제
     */
    @GetMapping(value = "/boardDelete")
    public String NoticeDelete(HttpServletRequest request, ModelMap model,HttpSession session) {

        log.info(this.getClass().getName() + ".BoardDelete start!");

        String msg = "";


        //널 체크를했는데 널이안머긍ㅁ
        String fileName = CmmUtil.nvl(request.getParameter("file"));
        String sfileName = CmmUtil.nvl(request.getParameter("sfile"));

        try {

            //업로드한 사진 삭제
            //파일 삭제
            if (!fileName.equals("null")&&!sfileName.equals("null")) {
                log.info(this.getClass().getName() + "파일삭제 시작");
                log.info(fileName);
                log.info(sfileName);
                int res = s3Service.fileDelete(fileName,sfileName); //파일삭제,썸네일 파일 삭제
//                 s3Service.sfileDelete(prev_sfile_name); //썸네일 파일 삭제
                log.info("res가 1이면 파일삭제 성공입니다" +res);
            }


            String Seq = CmmUtil.nvl(request.getParameter("Seq")); // 글번호(PK)
            String user_email = CmmUtil.nvl((String) session.getAttribute("SS_USER_EMAIL")); //이메일

            log.info("Seq : " + Seq);

            BoardDTO pDTO = new BoardDTO();
            pDTO.setSeq(Seq);
            pDTO.setUser_email(user_email);


            // 게시글 삭제하기 DB
            boardService.deleteNoticeInfo(pDTO);
            // 이미지게시글 삭제하기
            imgService.deleteFilePath(pDTO);

            msg = "삭제되었습니다.";

        } catch (Exception e) {
            msg = "실패하였습니다. : " + e.getMessage();
            log.info(e.toString());
            e.printStackTrace();

        } finally {
            log.info(this.getClass().getName() + ".BoardDelete end!");

            // 결과 메시지 전달하기
            model.addAttribute("msg", msg);
            model.addAttribute("url", "/boardList");

        }

        return "/redirect";
    }
}
