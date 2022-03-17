package kopo.poly.persistance.mapper;

import kopo.poly.vo.PageInfo;

import java.util.List;
import java.util.Map;

public interface IDicMapper {
    List<Map<String, Object>> getTitlelist(String colNm, PageInfo paging,String SORTNM)throws Exception;

    int getlistCount(String nm, String colNm)throws  Exception;

    List<Map<String, String>> getTitlelist(String colNm, String dicnm);
}
