package kopo.poly.controller;


import kopo.poly.service.IDicService;
import kopo.poly.util.CmmUtil;
import kopo.poly.util.Pagination;
import kopo.poly.vo.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@Slf4j
public class DicController {

    @Resource(name = "DicService")
    private IDicService dicservice;


    //Dictionary페이지 페이징, 검색 같이
    @GetMapping(value = "search")
    public String listPg(Model model, @RequestParam(value = "currentPage", // 태그의 name값
            required = false,       // 파라미터 필수 입력 여부 null값 → 1 [ required=true일 시, 400에러 ]
            defaultValue = "1") int currentPage,
                         HttpServletRequest request, HttpSession session) throws Exception {
        log.info(getClass().getName() + "Start SearchList ");

        //prameter 받은값
        String SORTNM = CmmUtil.nvl(request.getParameter("SORTNM"));    // 제목
        session.setAttribute("SORTNM", SORTNM);                        //제목 세션에 담고 넘겨버림
        String keyword = CmmUtil.nvl(request.getParameter("keyword")); //키워드 검색
        String sort = CmmUtil.nvl(request.getParameter("sort")); //구분값 검색

        log.info(this.getClass().getName() + sort);

        //키워드 검색
        Map<String, String> pMap = new HashMap<>();
        pMap.put("keyword", keyword);
        pMap.put("sort", sort);


        //게시글 총갯수
        int listCount = 196;

     // if(SORTNM !=null) {
    //       listCount = dicservice.getListCount(SORTNM);
    //   }else{
           listCount =196;
    //  }

        log.info("게시글총갯수확인" + listCount);

        // 클릭한 페이지, 총 게시글 수 전달
        PageInfo paging = Pagination.getPageInfo(currentPage, listCount);


        //게시글 목록
        List<Map<String, Object>> Titlelist = dicservice.getList(paging, SORTNM, pMap);

        //JSP로 전송하기
        model.addAttribute("Titlelist", Titlelist);
        model.addAttribute("paging", paging);


        log.info(getClass().getName() + "End SearchList ");
        return "/search/listPg";
    }

    //상세페이지
    @GetMapping(value = "infoPg")
    public String infoPg(HttpServletRequest request, Model model) {
        log.info(getClass().getName() + "Start info ");

        //url로 값 받아옴 
        String dicnm = CmmUtil.nvl(request.getParameter("DICNM"));

        log.info("dicnm :" + dicnm);

        //dicnm을 기준으로 설명문 담아오기
        List<Map<String, String>> InfoList = dicservice.getInfolist(dicnm);


        //JSP전송 
        model.addAttribute("InfoList", InfoList);


        log.info(getClass().getName() + "End info ");
        return "/search/infoPg";
    }

}
