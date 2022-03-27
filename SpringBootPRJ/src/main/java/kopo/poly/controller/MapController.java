package kopo.poly.controller;


import kopo.poly.service.IMapService;
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

@Slf4j
@Controller
public class MapController {

    @Resource(name = "MapService")
    private IMapService mapservice;

    //상세 검색 결과
    @GetMapping(value = "mapInfo")
    public String mapInfo(Model model, HttpServletRequest request) {
        log.info(getClass().getName() + "Start SearchList ");

        String GU_PLACE=CmmUtil.nvl(request.getParameter("GU_PLACE"));
        String GU_NAME=CmmUtil.nvl(request.getParameter("GU_NAME"));

        Map<String,String>pMap = new HashMap<>();

        pMap.put("GU_PLACE",GU_PLACE);
        pMap.put("GU_NAME",GU_NAME);


        List<Map<String,String>>getInfoList = mapservice.getMapListInfo(pMap);

        model.addAttribute("getInfoList",getInfoList);

        log.info(getClass().getName() + "End SearchList ");
        return "/map/mapInfo";
    }

    //검색목록페이지
    @GetMapping(value = "mapListInfo")
    public String mapListInfo(Model model, HttpServletRequest request,
                              @RequestParam(value = "currentPage", // 태그의 name값
                                      required = false,       // 파라미터 필수 입력 여부 null값 → 1 [ required=true일 시, 400에러 ]
                                      defaultValue = "1") int currentPage){

        log.info(getClass().getName() + "Start SearchList ");

        //parameter 받은값
        String gu = CmmUtil.nvl(request.getParameter("gu")); //구
        String dong = CmmUtil.nvl(request.getParameter("dong"));  //지역 동
        String keyword = CmmUtil.nvl(request.getParameter("keyword")); //키워드 검색
        String category = CmmUtil.nvl(request.getParameter("category")); //구분값 검색


        log.info(this.getClass().getName()+"dong"+dong);

        //키워드 검색
        Map<String, String> pMap = new HashMap<>();
        pMap.put("keyword", keyword);
        pMap.put("category", category);


        //게시글 총갯수
        int listCount =50;

//        if(SORTNM !=null) {
//            listCount = dicservice.getListCount(SORTNM,Search);
//        }else{
//            listCount =196;
//        }

        log.info("게시글총갯수확인" + listCount);

        // 클릭한 페이지, 총 게시글 수 전달
        PageInfo paging = Pagination.getPageInfo(currentPage, listCount);


        //게시글 목록 가져오기
        List<Map<String, Object>> MapList = mapservice.getMapList(paging,gu,dong,pMap);

        //JSP로 전송하기
        model.addAttribute("MapList", MapList);
        model.addAttribute("paging", paging);


        log.info(getClass().getName() + "End SearchList ");
        return "/map/mapListInfo";
    }

    //메인페이지
    @GetMapping(value = "mapSearch")
    public String mapSearch(HttpSession session) {
        log.info(getClass().getName() + "Start SearchList ");


        log.info(getClass().getName() + "End SearchList ");
        return "/map/mapSearch";
    }
    //메인페이지
    @GetMapping(value = "mp")
    public String mp(HttpSession session) {
        log.info(getClass().getName() + "Start SearchList ");


        log.info(getClass().getName() + "End SearchList ");
        return "/map/mp";
    }

}
