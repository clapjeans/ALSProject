package kopo.poly.service;

import kopo.poly.dto.MapDTO;
import kopo.poly.dto.SearchDTO;
import kopo.poly.vo.PageInfo;

import java.util.List;
import java.util.Map;

public interface IMapService {
    //검색
    List<MapDTO> getMapList(PageInfo paging, String gu, String dong, SearchDTO sDTO)throws Exception;



    //지도 정보가져오기
    List<MapDTO> getMapListInfo(MapDTO mDTO)throws Exception;

    //지도 카운트
    int getlistCount(SearchDTO sDTO ,String gu)throws Exception;

    int getAllListCount()throws Exception;
}
