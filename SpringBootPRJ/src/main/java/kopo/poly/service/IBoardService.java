package kopo.poly.service;

import kopo.poly.dto.BoardDTO;
import org.bson.Document;


import java.util.List;




public interface IBoardService {

	//게시판리스트가져오기
	List<Document> getNoticeList() throws Exception;

	//게시판 저장하기
	int InsertNoticeInfo(BoardDTO pDTO) throws Exception;

	//게시판 정보가져오기
	BoardDTO getNoticeInfo(BoardDTO pDTO) throws Exception;

	//게시판 수정하기
	int updateNoticeInfo(BoardDTO pDTO) throws Exception;

	//파일삭제
	int deleteNoticeInfo(BoardDTO pDTO) throws Exception;

}

