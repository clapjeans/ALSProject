package kopo.poly.persistance.mapper;

import kopo.poly.dto.DicDTO;
import kopo.poly.vo.PageInfo;

import java.util.List;
import java.util.Map;

public interface IDicMapper {


    /**
     *
     * @param colNm 조회할 컬렉션 이름
     * @param paging 페이징 갯수
     * @param SORTNM 폐기물 분류 ex) 불연성 비닐류 대형폐기물
     * @param
     * @return
     * @throws Exception
     */
    //페이징 검색기능
    List<DicDTO> getTitlelist(String colNm, PageInfo paging, String SORTNM, DicDTO pDTP)throws Exception;

    //게시물 갯수가져오기
    int getlistCount( String colNm,String sortnm)throws  Exception;

    //재활용정보가져오기
    List<DicDTO> getInfolist(String colNm, String dicnm);


    //게시물 갯수가져오기
    int getkeyCount(String colNm, DicDTO pDTO);
}
