package kopo.poly.controller;


import kopo.poly.dto.MapDTO;
import kopo.poly.dto.SearchDTO;
import kopo.poly.service.IMapService;
import kopo.poly.util.CmmUtil;
import kopo.poly.util.EncryptUtil;
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

@Slf4j
@Controller
public class MapController {

    @Resource(name = "MapService")
    private IMapService mapservice;

    //지도 메인페이지
    @GetMapping(value = "mapSearch")
    public String mapSearch(HttpSession session) {
        log.info(getClass().getName() + "Start SearchList");


        log.info(getClass().getName() + "End SearchList ");
        return "/map/mapSearch";
    }


    //지역별 분리배출 방법 상세 검색 결과 url로 파라미터 담아 가져옴
    @GetMapping(value = "mapInfo")
    public String mapInfo(Model model, HttpServletRequest request) throws Exception {
        log.info(getClass().getName() + "Start SearchList ");
        log.info("받은 단어 : " + request.getParameter("GU_PLACE"));
        //String test = EncryptUtil.decAES128CBC(request.getParameter("GU_PLACE"));
        //String gu_name = URLDecoder.decode(test, "b");



        //log.info("gu_name : " + test);
       // request.setCharacterEncoding("UTF-8");

     //  String GU_PLACE = java.net.URLDecoder.decode(request.getParameter("GU_PLACE"), "EUC-KR");
       // log.info(GU_PLACE);
     String gu= request.getParameter("GU_PLACE");
//        log.info("GU_PLACE"+CmmUtil.nvl(URLDecoder.decode(request.getParameter("GU_PLACE"))));
//        log.info("GU_NAME"+URLDecoder.decode(CmmUtil.nvl(request.getParameter("GU_NAME")),"EUC-KR"));
//     log.info("GU_NAME"+URLDecoder.decode(CmmUtil.nvl(request.getParameter("GU_NAME")),"UTF-8"));
//
//     String gu_name = java.net.URLDecoder.decode(gu,"EUC-KR");
//
//      log.info(URLDecoder.decode(gu_name,"EUC-KR"));
        String GU_PLACE = request.getParameter("GU_PLACE");
   String GU_NAME = request.getParameter("GU_NAME");
    log.info("GU_PLACE"+GU_PLACE);
    log.info("GU_NAME"+GU_NAME);


        MapDTO mDTO = new MapDTO();

        mDTO.setGu_place(CmmUtil.nvl(request.getParameter("GU_PLACE")));
        mDTO.setGu_name(CmmUtil.nvl(request.getParameter("GU_NAME")));


        List<MapDTO>getInfoList = mapservice.getMapListInfo(mDTO);

        model.addAttribute("getInfoList",getInfoList);
        log.info(getClass().getName() + "End SearchList ");
        return "/map/mapInfo";
    }

    //검색목록페이지
    @GetMapping(value = "mapListInfo")
    public String mapListInfo(Model model, HttpServletRequest request,
                              @RequestParam(value = "currentPage", // 태그의 name값
                                      required = false,       // 파라미터 필수 입력 여부 null값 → 1 [ required=true일 시, 400에러 ]
                                      defaultValue = "1") int currentPage) throws Exception {

        log.info(getClass().getName() + "Start SearchList ");



        //parameter 받은값
        String gu = CmmUtil.nvl(request.getParameter("gu")); //구
        String dong = CmmUtil.nvl(request.getParameter("dong"));  //지역 동
        String category = CmmUtil.nvl(request.getParameter("category")); //카테고리

        log.info(this.getClass().getName()+"gu"+gu);
        log.info(this.getClass().getName()+"dong"+dong);

        //키워드 검색
        SearchDTO sDTO = new SearchDTO();
        sDTO.setCategory(CmmUtil.nvl(request.getParameter("category")));
        sDTO.setKeyword(CmmUtil.nvl(request.getParameter("keyword")));




        //게시글 총갯수
        int listCount = 0;

        // 게시글 총 갯수
        listCount = mapservice.getAllListCount();

        if (!category.equals("")||!gu.equals("")) {

            listCount = mapservice.getlistCount(sDTO,gu);
        }

        log.info("게시글총갯수확인" + listCount);

        // 클릭한 페이지, 총 게시글 수 전달
        PageInfo paging = Pagination.getPageInfo(currentPage, listCount);


        //게시글 목록 가져오기
        List<MapDTO> MapList = mapservice.getMapList(paging,gu,dong,sDTO);

        //JSP로 전송하기
        model.addAttribute("MapList", MapList);
        model.addAttribute("paging", paging);


        log.info(getClass().getName() + "End SearchList ");
        return "/map/mapListInfo";
    }


//    //메인페이지
//    @GetMapping(value = "mp")
//    public String mp(HttpSession session) {
//        log.info(getClass().getName() + "Start SearchList ");
//
//
//        log.info(getClass().getName() + "End SearchList ");
//        return "/map/mp";
//    }

}
