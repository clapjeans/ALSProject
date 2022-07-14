package kopo.poly.persistance.mapper;

import kopo.poly.dto.MapDTO;
import kopo.poly.dto.SearchDTO;
import kopo.poly.vo.PageInfo;

import java.util.List;
import java.util.Map;

public interface IMapMapper {

    //게시물 리스트가져오기
    List<MapDTO> getMapList(PageInfo paging, String gu, String dong, SearchDTO sDTO )throws Exception;

    //map 검색 리스트 가졍기
    List<MapDTO> getMapInfoList(MapDTO mDTO)throws Exception;

    int getListCount(SearchDTO sDTO,String gu)throws Exception;

    int getAllListCount()throws Exception;
}
