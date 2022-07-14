package kopo.poly.persistance.mapper;

import kopo.poly.dto.BoardDTO;
import kopo.poly.dto.ImgDTO;
import org.bson.Document;

import java.util.List;


public interface IBoardMapper {

	//게시판 리스트
	List<Document> getNoticeList(String ColNm) throws Exception;
	
	//게시판 글 등록
	int InsertNoticeInfo(BoardDTO pDTO,String colNm) throws Exception;
	
	//게시판 상세보기
	BoardDTO getNoticeInfo(BoardDTO pDTO,String colNm) throws Exception;

	//게시판 조회수 업데이트
	void updateNoticeReadCnt(BoardDTO pDTO) throws Exception;
	
	//게시판 글 수정
	int updateNoticeInfo(BoardDTO pDTO ,String colNm) throws Exception;
	
	//게시판 글 삭제
	int deleteNoticeInfo(BoardDTO pDTO,String colNm) throws Exception;



}
