package kopo.poly.persistance.mapper;

import kopo.poly.vo.PageInfo;

import java.util.List;
import java.util.Map;

public interface IMapMapper {

    //게시물 리스트가져오기
    List<Map<String, Object>> getMapList(PageInfo paging, String gu, String dong, Map<String, String> pMap);

    //map 검색 리스트 가졍기
    List<Map<String, String>> getMapInfoList(Map<String, String> pMap);

}
