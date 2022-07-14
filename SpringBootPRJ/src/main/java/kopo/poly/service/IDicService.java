package kopo.poly.service;

import kopo.poly.dto.DicDTO;
import kopo.poly.vo.PageInfo;

import java.util.List;

public interface IDicService {

    //listpage DICNM 가져오기
    List<DicDTO> getList(PageInfo paging, String SORTNM, DicDTO pDTO) throws Exception;

    //게시물 수 가져오기
    int getListCount(String sortnm) throws Exception;

    //게시물 정보가져오기
    List<DicDTO> getInfolist(String dicnm) throws Exception;

    //게시물 수 가져오기
    int getKeyCount(DicDTO pDTO) throws Exception;
}
