package kopo.poly.service;

import kopo.poly.dto.BoardDTO;
import kopo.poly.dto.ImgDTO;

public interface IImgService {

    //이미지 경로저장
    int insertFilePath(ImgDTO fDTO)throws Exception;

    //이미지 경로 가져오기
    ImgDTO getFilePath(String seq)throws Exception;

    //이미지 삭제
    int deleteFilePath(BoardDTO pDTO)throws Exception;

    //이미지 업데이트
    int updateFileUpload(ImgDTO fDTO);
}
