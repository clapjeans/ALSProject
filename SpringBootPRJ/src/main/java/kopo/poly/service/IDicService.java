package kopo.poly.service;

import kopo.poly.vo.PageInfo;

import java.util.List;
import java.util.Map;

public interface IDicService {

    //listpage DICNM 가져오기
    List<Map<String, Object>> getList(PageInfo paging, String SORTNM)throws Exception;

    //게시물 수 가져오기
    int getListCount(String sortnm)throws Exception;

    //게시물 정보가져오기
    List<Map<String, String>> getInfolist(String dicnm);
}
