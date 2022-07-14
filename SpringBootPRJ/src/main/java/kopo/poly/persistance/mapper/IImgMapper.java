package kopo.poly.persistance.mapper;


import kopo.poly.dto.BoardDTO;
import kopo.poly.dto.ImgDTO;
import org.apache.ibatis.annotations.Mapper;

public interface IImgMapper {
	

	//파일 경로 저장하기
	 int insertFilePath(ImgDTO fDTO,String colnm) throws Exception;

    // path 가져오기
    ImgDTO getFilePath(String seq,String colnm)throws Exception;


    //파일 경로 저장하기
    int deleteFilePath(BoardDTO pDTO, String colNm) throws  Exception;

    //파일 업데이트 하기
    int updateFileUpload(ImgDTO fDTO, String colNm);
}
