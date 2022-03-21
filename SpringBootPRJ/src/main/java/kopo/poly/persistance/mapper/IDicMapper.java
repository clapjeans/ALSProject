package kopo.poly.persistance.mapper;

import kopo.poly.vo.PageInfo;

import java.util.List;
import java.util.Map;

public interface IDicMapper {

    //페이징 검색기능
    List<Map<String, Object>> getTitlelist(String colNm, PageInfo paging,String SORTNM,Map<String, String> pMap)throws Exception;
//
//    //게시물 갯수가져오기
//    int getlistCount(String nm, String colNm)throws  Exception;

    //재활용정보가져오기
    List<Map<String, String>> getTitlelist(String colNm, String dicnm);
}
