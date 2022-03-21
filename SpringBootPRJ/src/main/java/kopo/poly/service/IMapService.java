package kopo.poly.service;

import kopo.poly.vo.PageInfo;

import java.util.List;
import java.util.Map;

public interface IMapService {
    //검색
    List<Map<String, Object>> getMapList(PageInfo paging, String gu, String dong, Map<String, String> pMap);


    //지도 정보가져오기
    List<Map<String, String>> getMapListInfo(Map<String, String> pMap);
}
