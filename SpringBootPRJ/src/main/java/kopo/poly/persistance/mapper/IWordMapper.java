package kopo.poly.persistance.mapper;

import kopo.poly.dto.ChatDTO;
import kopo.poly.dto.DicDTO;

import java.util.List;

public interface IWordMapper {

    //해당되는단어가 있는지 없는지 확인하기
    List<DicDTO> WordSearch(List<String> rList)throws Exception;
}
